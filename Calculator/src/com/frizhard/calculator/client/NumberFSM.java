package com.frizhard.calculator.client;

public class NumberFSM implements FSM<String> {

	enum State {
		ZERO,
		DIGIT,
		DECIMAL,
	}
	
	// TODO: state
	private String number = Constants.Digit0;
	private String signum = Constants.SignumPositive;
	private State state = State.ZERO;
	
	@Override
	public void reset() {
		// TODO: reset state
		number = Constants.Digit0;
		signum = Constants.SignumPositive;
		state = State.ZERO;
	}

	@Override
	public void feedInput(String input) {
		boolean isOk = true;
		
		switch(Constants.inputTypeForString(input)) {
		case DIGIT:
			isOk = feedDigit(input);
			break;
			
		case MODIFIER:
			isOk = feedModifier(input);
			break;
			
		case COMMAND:
		case OPERATOR:
		case UNKNOWN:
			isOk = false;
			break;
		}
		
		if(!isOk) {
			// TODO: warn user 
		}
	}
	
	public String value() {
		return signum + number;
	}
	
	// TODO: these methods modify state. Refactor to return the new state in order to be testable
	private boolean feedDigit(String digit) {
		if(state != State.ZERO) {
			number = number + digit;
		} else {
			if(!digit.equals(Constants.Digit0)) {
				number = "" + digit;
				state = State.DIGIT;
			}
		}
		
		return true;
	}
	
	private boolean feedModifier(String modifier) {
		if(modifier.equals(Constants.ModifierPercent)) {
			return false;
		} else if(modifier.equals(Constants.ModifierSignum)) {
			signum = (signum.equals(Constants.SignumPositive)) ? Constants.SignumNegative : Constants.SignumPositive;
		} else if(modifier.equals(Constants.ModifierDot)) {
			switch (state) {
			case ZERO:
				number = Constants.Digit0 + Constants.ModifierDot;
				state = State.DECIMAL;
				break;
				
			case DIGIT:
				number = number + Constants.ModifierDot;
				state = State.DECIMAL;
				break;
				
			case DECIMAL:
				break;
			}
		}
		
		return true;
	}

}
