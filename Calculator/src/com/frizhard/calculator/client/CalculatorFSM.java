package com.frizhard.calculator.client;

public class CalculatorFSM implements FSM<String> {
	
	// TODO: states. 
	
	public void reset() {
		// TODO: Reset state
	}
	
	// TODO: Do something useful with input
	public void feedInput(String input) {
		switch(Constants.inputTypeForString(input)) {
		case COMMAND:
			break;
		case DIGIT:
			break;
		case MODIFIER:
			break;
		case OPERATOR:
			break;
		case UNKNOWN:
			break;
		}
	}
}
