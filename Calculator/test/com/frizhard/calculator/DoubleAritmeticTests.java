package com.frizhard.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.frizhard.calculator.client.Constants;
import com.frizhard.calculator.client.DoubleAritmetic;

public class DoubleAritmeticTests {

	@Test
	public void testOneOperand() {
		DoubleAritmetic op = new DoubleAritmetic();
		
		assertTrue(op.operate(null, null, "1").equals("1"));
	}
	
	public void testIntegerArithmetic() {
		DoubleAritmetic op = new DoubleAritmetic();
		
		assertTrue(op.operate("20", Constants.OperatorSum, "5").equals("25"));
		assertTrue(op.operate("3", Constants.OperatorSubtract, "10").equals("-7"));
		assertTrue(op.operate("7", Constants.OperatorMultiply, "-8").equals("-56"));
		assertTrue(op.operate("20", Constants.OperatorDivision, "5").equals("4"));
	}
	
	public void testDoubleArithmetic() {
		DoubleAritmetic op = new DoubleAritmetic();
		
		assertTrue(op.operate("20,78", Constants.OperatorSum, "5,").equals("25,78"));
		assertTrue(op.operate("3,001", Constants.OperatorSubtract, "10,002").equals("-7,001"));
		assertTrue(op.operate("7,12", Constants.OperatorMultiply, "-8,45").equals("-60,184"));
		assertTrue(op.operate("100,5", Constants.OperatorDivision, "2,5").equals("40,2"));
	}
	
	public void testErrors() {
		DoubleAritmetic op = new DoubleAritmetic();
		
		assertTrue(op.operate("1", null, null).equals(Constants.Error));
		assertTrue(op.operate("2", Constants.OperatorDivision, "0").equals(Constants.Error));
		assertTrue(op.operate("2,5", Constants.OperatorDivision, "0,00000").equals(Constants.Error));
	}

}
