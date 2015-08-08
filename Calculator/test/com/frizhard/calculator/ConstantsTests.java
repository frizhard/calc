package com.frizhard.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.frizhard.calculator.client.Constants;

/**
 * Tests unitarios para las constantes
 * 
 * @author jose
 */
public class ConstantsTests {

	@Test
	public void testInputType() {
		assertTrue(Constants.inputTypeForString(Constants.CommandClear) == Constants.InputType.COMMAND);
		assertTrue(Constants.inputTypeForString(Constants.CommandClearEntry) == Constants.InputType.COMMAND);
		assertTrue(Constants.inputTypeForString(Constants.CommandEqual) == Constants.InputType.COMMAND);
		
		assertTrue(Constants.inputTypeForString(Constants.Digit0) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit1) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit2) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit3) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit4) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit5) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit6) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit7) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit8) == Constants.InputType.DIGIT);
		assertTrue(Constants.inputTypeForString(Constants.Digit9) == Constants.InputType.DIGIT);
		
		assertTrue(Constants.inputTypeForString(Constants.ModifierDot) == Constants.InputType.MODIFIER);
		assertTrue(Constants.inputTypeForString(Constants.ModifierPercent) == Constants.InputType.MODIFIER);
		assertTrue(Constants.inputTypeForString(Constants.ModifierSignum) == Constants.InputType.MODIFIER);
		
		assertTrue(Constants.inputTypeForString(Constants.OperatorSum) == Constants.InputType.OPERATOR);
		assertTrue(Constants.inputTypeForString(Constants.OperatorSubtract) == Constants.InputType.OPERATOR);
		assertTrue(Constants.inputTypeForString(Constants.OperatorMultiply) == Constants.InputType.OPERATOR);
		assertTrue(Constants.inputTypeForString(Constants.OperatorDivision) == Constants.InputType.OPERATOR);
		
		assertTrue(Constants.inputTypeForString(null) == Constants.InputType.UNKNOWN);
		assertTrue(Constants.inputTypeForString("RANDOM STRING") == Constants.InputType.UNKNOWN);
		assertTrue(Constants.inputTypeForString("ANOTHER RANDOM STRING") == Constants.InputType.UNKNOWN);
		assertTrue(Constants.inputTypeForString("") == Constants.InputType.UNKNOWN);
	}

}
