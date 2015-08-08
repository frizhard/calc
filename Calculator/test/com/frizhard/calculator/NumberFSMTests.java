package com.frizhard.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.frizhard.calculator.client.Constants;
import com.frizhard.calculator.client.NumberFSM;

/**
 * Tests unitarios para la FSM de números
 * 
 * @author jose
 */
public class NumberFSMTests {

	@Test
	public void testNewFSM() {
		NumberFSM fsm = new NumberFSM();
		
		assertTrue(fsm.value().equals(Constants.Digit0));
	}
	
	@Test
	public void testZero() {
		NumberFSM fsm = new NumberFSM();
		
		fsm.feedInput(Constants.Digit0);
		
		assertTrue(fsm.value().equals(Constants.Digit0));
	}
	
	@Test
	public void testZeros() {
		NumberFSM fsm = new NumberFSM();
		
		int zeros = 5;
		for(int i = 0;i < zeros;i++) {
			fsm.feedInput(Constants.Digit0);
		}
		
		assertTrue(fsm.value().equals(Constants.Digit0));
	}

	@Test
	public void testDigit() {
		NumberFSM fsm = new NumberFSM();
		
		fsm.feedInput(Constants.Digit8);
		
		assertTrue(fsm.value().equals(Constants.Digit8));
	}
	
	@Test
	public void testDigits() {
		NumberFSM fsmUp = new NumberFSM();
		NumberFSM fsmDown = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.Digit2, Constants.Digit3, Constants.Digit4, Constants.Digit5,
								Constants.Digit6, Constants.Digit7, Constants.Digit8, Constants.Digit9 };
		
		int inputCount = inputs.length;
		for(int i = 0;i<inputCount;i++) {
			fsmUp.feedInput(inputs[i]);
			fsmDown.feedInput(inputs[inputCount-1-i]);
		}
		
		assertTrue(fsmUp.value().equals("123456789"));
		assertTrue(fsmDown.value().equals("987654321"));
	}
	
	@Test
	public void testZerosAndDigits() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit0, Constants.Digit0, Constants.Digit1, Constants.Digit2, Constants.Digit3,
								Constants.Digit0, Constants.Digit0, Constants.Digit0, Constants.Digit4 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("1230004"));
	}
	
	@Test
	public void testNonNumbers() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.CommandClear, Constants.CommandClearEntry, Constants.Digit1, Constants.Digit2, 
								Constants.OperatorMultiply, Constants.OperatorSubtract, Constants.OperatorSum, Constants.Digit3 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("123"));
	}
	
	@Test
	public void testSignum() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.ModifierSignum };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("-1"));
	}
	
	@Test
	public void testSignum2() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.ModifierSignum, Constants.Digit1 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("-1"));
	}
	
	@Test
	public void testSignum3() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.ModifierSignum, Constants.Digit2 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("-12"));
	}
	
	@Test
	public void testSigni() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.ModifierSignum, Constants.Digit2, Constants.ModifierSignum };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("12"));
	}
	
	@Test
	public void testDot() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.ModifierDot };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("1,"));
	}
	
	@Test
	public void testDot2() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.ModifierDot, Constants.Digit1 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("0,1"));
	}
	
	@Test
	public void testDot3() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.ModifierDot };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("0,"));
	}
	
	@Test
	public void testDot4() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.Digit2, Constants.ModifierDot, Constants.Digit3 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("12,3"));
	}
	
	@Test
	public void testDotZeros() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit0, Constants.Digit0, Constants.ModifierDot, Constants.Digit0, Constants.Digit0, Constants.Digit1 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("0,001"));
	}
	
	@Test
	public void testDots() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.ModifierDot, Constants.Digit2, Constants.ModifierDot, Constants.Digit3 };
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("1,23"));
	}
	
	@Test
	public void testAllInput() {
		NumberFSM fsm = new NumberFSM();
		
		String inputs [] = { 	Constants.Digit1, Constants.Digit2, Constants.ModifierSignum, Constants.Digit3, 
								Constants.Digit4, Constants.ModifierDot, Constants.Digit5, Constants.Digit6,
								Constants.CommandEqual, Constants.OperatorDivision};
		
		for(String input : inputs) {
			fsm.feedInput(input);
		}
		
		assertTrue(fsm.value().equals("-1234,56"));
	}
}
