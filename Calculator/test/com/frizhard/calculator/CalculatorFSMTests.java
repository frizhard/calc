package com.frizhard.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.frizhard.calculator.client.CalculatorFSM;
import com.frizhard.calculator.client.Constants;

/**
 * Tests unitarios para la FSM de calculadora
 * 
 * @author jose
 */
public class CalculatorFSMTests {

	@Test
	public void testZero() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		assertTrue(calc.getOutput().equals(Constants.Digit0));
		
		String inputs[] = { Constants.Digit0 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals(Constants.Digit0));
	}
	
	@Test
	public void testZeros() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit0, Constants.Digit0, Constants.Digit0 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals(Constants.Digit0));
	}
	
	@Test
	public void testPositive() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit0, Constants.Digit0, Constants.Digit1, Constants.Digit2, Constants.Digit0 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("120"));
	}

	@Test
	public void testNegative() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit0, Constants.ModifierSignum, Constants.Digit0, Constants.Digit1, Constants.Digit2, Constants.Digit0 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("-120"));
	}
	
	@Test
	public void testFractional() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit0, Constants.Digit2 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("12,02"));
	}
	
	@Test
	public void testDots() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit0, Constants.ModifierDot, 
				Constants.Digit2, Constants.ModifierDot, Constants.Digit3 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("12,023"));
	}
	
	@Test
	public void testOperand() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.OperatorSum, Constants.Digit3, Constants.Digit4 };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("34"));
	}
	
	@Test
	public void testIntegerSum() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.OperatorSum, Constants.Digit3, Constants.Digit4, 
				Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("46"));
	}
	
	@Test
	public void testFractionalSum() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit3, Constants.OperatorSum, 
				Constants.ModifierSignum, Constants.Digit3, Constants.Digit4, Constants.ModifierDot, Constants.Digit5, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("-22,2"));
	}
	
	@Test
	public void testClear() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit3, Constants.OperatorSum, 
				Constants.ModifierSignum, Constants.Digit3, Constants.Digit4, Constants.ModifierDot, Constants.Digit5, 
				Constants.CommandClear, Constants.Digit1, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("1"));
	}
	
	@Test
	public void testClearEntry() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit3, Constants.OperatorSum, 
				Constants.ModifierSignum, Constants.Digit3, Constants.Digit4, Constants.ModifierDot, Constants.Digit5, 
				Constants.CommandClearEntry, Constants.Digit1, Constants.Digit2, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("24,3"));
	}
	
	@Test
	public void testSingleEqual() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("12"));
	}
	
	@Test
	public void testSeveralEquals() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.Digit2, Constants.CommandEqual, Constants.CommandEqual, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("12"));
	}
	
	@Test
	public void testOperators() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.OperatorSum, Constants.Digit2, Constants.OperatorSubtract, 
				Constants.Digit1, Constants.OperatorMultiply, Constants.Digit4, Constants.OperatorDivision, Constants.Digit2,
				Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("4"));
	}
	
	@Test
	public void testOperatorChange() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit1, Constants.OperatorSubtract, Constants.OperatorMultiply, Constants.OperatorDivision,
				Constants.OperatorSum, Constants.Digit2, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("3"));
	}
	
	@Test
	public void testPercent() {
		CalculatorFSM calc = new CalculatorFSM(null);
		
		String inputs[] = { Constants.Digit5, Constants.Digit0, Constants.OperatorSum, Constants.Digit1, Constants.Digit0, 
				Constants.ModifierPercent, Constants.CommandEqual };
		
		for(String input : inputs) {
			calc.feedInput(input);
		}
		
		assertTrue(calc.getOutput().equals("55"));
	}
}
