package com.frizhard.calculator.client;

/**
 * Implementación del interface Aritmetic, convirtiendo los números a doubles antes de operar y
 * volviendo a convertir el resultado a cadena.
 * 
 * Ver tests unitarios en DoubleAritmeticTests
 * 
 * @author jose
 */
public class DoubleAritmetic implements Aritmetic {

	@Override
	public String operate(String operand1, String operator, String operand2) {
		double op1 = 0.0;
		double op2 = 0.0;
		try {
			op1 = (operand1 == null) ? 0.0 : Double.parseDouble(operand1.replace(Constants.ModifierDot, Constants.AritmeticDot));
			op2 = (operand2 == null) ? 0.0 : Double.parseDouble(operand2.replace(Constants.ModifierDot, Constants.AritmeticDot));
		} catch(Exception e) {
			return Constants.Error;
		}
		
		if(operand1 == null && operator == null && operand2 != null) {
			return new String(operand2);
		} else if(operand2 == null) {
			return Constants.Error;
		}
		
		if(op2 == 0.0 && operator.equals(Constants.OperatorDivision)) {
			return Constants.Error;
		} else {
			double result = 0.0;
			try {
				if(operator.equals(Constants.OperatorSum)) {
					result = op1 + op2;
				} else if(operator.equals(Constants.OperatorSubtract)) {
					result = op1 - op2;
				} else if(operator.equals(Constants.OperatorMultiply)) {
					result = op1 * op2;
				} else if(operator.equals(Constants.OperatorDivision)) {
					result = op1 / op2;
				}
			} catch(Exception e) {
				return Constants.Error;
			}
			
			double floorResult = Math.floor(result); 
			if(result == floorResult) {
				int intResult = (int)floorResult;
				return ("" + intResult);
			} else {
				return ("" + result).replace(Constants.AritmeticDot, Constants.ModifierDot);
			}
		}
	}

}
