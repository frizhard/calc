package com.frizhard.calculator.client;

public class CalculatorFSM implements FSM<String> {
	
	enum State {
		WAITING_INPUT,
		AFTER_OPERATOR,
		AFTER_EQUAL,
		AFTER_ERROR,
		AFTER_PERCENT
	}
	
	String accumulated = null;
	String operator = null;
	String output = Constants.Digit0;
	NumberFSM numericInput = new NumberFSM();
	State state = State.WAITING_INPUT;
	
	Aritmetic aritmetic = new DoubleAritmetic();	// So we can switch to StringAritmetic
	
	CalculatorOutput delegate = null;
	
	public CalculatorFSM(CalculatorOutput delegate) {	
		this.delegate = delegate;
	}
	
	private void setOutput(String output) {
		if (delegate != null) {
			delegate.outputDidChangeTo(output);
		}
	}
	
	public String getOutput() {
		return (output != null) ? new String(output) : null;
	}
	
	public void reset() {
		accumulated = null;
		operator = null;
		output = Constants.Digit0;
		numericInput.reset();
		state = State.WAITING_INPUT;
	}
	
	// TODO: There are missing cases, check them out
	public void feedInput(String input) {
		switch(Constants.inputTypeForString(input)) {
		case COMMAND:
			if(input.equals(Constants.CommandClear)) {
				reset();
				setOutput(output);
				state = State.WAITING_INPUT;
			} else if (input.equals(Constants.CommandClearEntry)) {
				numericInput.reset();
				output = numericInput.value();
				setOutput(output);
				
				if(state == State.AFTER_EQUAL || state == State.AFTER_PERCENT) {
					state = State.WAITING_INPUT;
				}
			} else if (input.equals(Constants.CommandEqual)) {
				if(state != State.AFTER_PERCENT) {
					output = aritmetic.operate(accumulated, operator, numericInput.value());
				} else {
					output = aritmetic.operate(accumulated, operator, output);
				}
				accumulated = null;
				operator = null;
				numericInput.reset();
				setOutput(output);
				state = output.equals(Constants.Error) ? State.AFTER_ERROR : State.AFTER_EQUAL;
			}
			break;
			
		case DIGIT:
			numericInput.feedInput(input);
			output = numericInput.value();
			setOutput(output);
			state = State.WAITING_INPUT;
			break;
			
		case MODIFIER:
			if(input.equals(Constants.ModifierPercent)) {
				// TODO: calculate percent
				String percent = aritmetic.operate(numericInput.value(), Constants.OperatorDivision, "100");
				output = aritmetic.operate(accumulated, Constants.OperatorMultiply, percent);
				setOutput(output);
				state = State.AFTER_PERCENT;
			} else {
				numericInput.feedInput(input);
				output = numericInput.value();
				setOutput(output);
			}
			break;
			
		case OPERATOR:
			if(state == State.AFTER_OPERATOR) {
				operator = new String(input);
			} else if (state == State.AFTER_EQUAL) {
				accumulated = new String(output);
				operator = new String(input);
				state = State.AFTER_OPERATOR;
			} else if (state == State.AFTER_ERROR) {
				accumulated = Constants.Digit0;
				operator = new String(input);
				state = State.AFTER_OPERATOR;
				output = numericInput.value();
				setOutput(output);
			} else {
				// TODO: calculate accumulated
				accumulated = aritmetic.operate(accumulated, operator, numericInput.value());
				operator = new String(input);
				output = new String(accumulated);
				numericInput.reset();
				setOutput(output);
				state = State.AFTER_OPERATOR;
			}
			break;
			
		case UNKNOWN:
			break;
		}
	}
}
