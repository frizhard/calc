package com.frizhard.calculator.client;

/**
 * Máquina de estados finitos que representa la calculadora.
 * 
 * Las operaciones se realizan con la clase DoubleAritmetic, por lo que puede
 * haber pérdida de precisión. Para ser más correcto se implementaría una
 * clase que trabajara directamente con las cadenas de texto.
 * 
 * Ver los tests unitarios de funcionamiento en CalculatorFSMTests
 * 
 * Internamente utiliza otra FSM de tipo NumberFSM para reconocer los números y
 * simplificar los estados y que no tenga que reconocer cada dígito pulsado
 * 
 * Notifica a su delegado, si lo hay, cuando cambia la salida en pantalla
 * 
 * @author jose
 */

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
	
	public void feedInput(String input) {
		switch(Constants.inputTypeForString(input)) {
		case COMMAND:
			feedCommand(input);
			break;
			
		case DIGIT:
			feedDigit(input);
			break;
			
		case MODIFIER:
			feedModifier(input);
			break;
			
		case OPERATOR:
			feedOperator(input);
			break;
			
		case UNKNOWN:
			break;
		}
	}

	private void feedOperator(String input) {
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
			accumulated = aritmetic.operate(accumulated, operator, numericInput.value());
			operator = new String(input);
			output = new String(accumulated);
			numericInput.reset();
			setOutput(output);
			state = State.AFTER_OPERATOR;
		}
	}

	private void feedModifier(String input) {
		if(input.equals(Constants.ModifierPercent)) {
			String percent = aritmetic.operate(numericInput.value(), Constants.OperatorDivision, "100");
			output = aritmetic.operate(accumulated, Constants.OperatorMultiply, percent);
			setOutput(output);
			state = State.AFTER_PERCENT;
		} else {
			numericInput.feedInput(input);
			output = numericInput.value();
			setOutput(output);
		}
	}

	private void feedDigit(String input) {
		numericInput.feedInput(input);
		output = numericInput.value();
		setOutput(output);
		state = State.WAITING_INPUT;
	}

	private void feedCommand(String input) {
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
			if (state != State.AFTER_EQUAL) {
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
		}
	}
}
