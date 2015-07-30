package com.frizhard.calculator.client;

import java.util.Arrays;
import java.util.HashSet;

enum InputType {
	COMMAND,
	DIGIT,
	MODIFIER,
	OPERATOR,
	UNKNOWN
}

public class Constants {
	public final static String CommandClear = "C";
	public final static String CommandClearEntry = "CE";
	public final static String CommandEqual = "=";
	
	public final static String Digit0 = "0";
	public final static String Digit1 = "1";
	public final static String Digit2 = "2";
	public final static String Digit3 = "3";
	public final static String Digit4 = "4";
	public final static String Digit5 = "5";
	public final static String Digit6 = "6";
	public final static String Digit7 = "7";
	public final static String Digit8 = "8";
	public final static String Digit9 = "9";
	
	public final static String ModifierSignum = "+/-";
	public final static String ModifierPercent = "%";
	public final static String ModifierDot = ",";
	
	public final static String OperatorSum = "+";
	public final static String OperatorSubtract = "-";
	public final static String OperatorMultiply = "*";
	public final static String OperatorDivision = "/";
	
	private static final HashSet<String> commands = new HashSet<String>(Arrays.asList(CommandClear, CommandClearEntry, CommandEqual));
	private static final HashSet<String> digits = new HashSet<String>(Arrays.asList(Digit0, Digit1, Digit2, Digit3, Digit4, Digit5, Digit6, Digit7, Digit8, Digit9));
	private static final HashSet<String> modifiers = new HashSet<String>(Arrays.asList(ModifierSignum, ModifierPercent, ModifierDot));
	private static final HashSet<String> operators = new HashSet<String>(Arrays.asList(OperatorSum, OperatorSubtract, OperatorMultiply, OperatorDivision));
	
	private static boolean isCommand(String input) {
		return (input != null && commands.contains(input));
	}
	
	private static boolean isDigit(String input) {
		return (input != null && digits.contains(input));
	}
	
	private static boolean isModifier(String input) {
		return (input != null && modifiers.contains(input));
	}
	
	private static boolean isOperator(String input) {
		return (input != null && operators.contains(input));
	}
	
	public static InputType inputTypeForString(String input) {
		if(input != null) {
			if (isCommand(input)) {
				return InputType.COMMAND;
			} else if (isDigit(input)) {
				return InputType.DIGIT;
			} else if (isModifier(input)) {
				return InputType.MODIFIER;
			} else if (isOperator(input)) {
				return InputType.OPERATOR;
			}
		}
		
		return InputType.UNKNOWN;
	}
}
