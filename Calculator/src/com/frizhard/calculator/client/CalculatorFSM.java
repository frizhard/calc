package com.frizhard.calculator.client;

public class CalculatorFSM {
	
	// TODO: states. 
	
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
