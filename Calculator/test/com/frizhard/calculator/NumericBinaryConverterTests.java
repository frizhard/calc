package com.frizhard.calculator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.frizhard.calculator.server.NumericBinaryConverter;

public class NumericBinaryConverterTests {

	@Test
	public void testZeros() {
		NumericBinaryConverter converter = new NumericBinaryConverter();
		
		assertTrue(converter.convertNumber(null).equals("0"));
		assertTrue(converter.convertNumber("0").equals("0"));
	}
	
	@Test
	public void testPositives() {
		NumericBinaryConverter converter = new NumericBinaryConverter();
		
		assertTrue(converter.convertNumber("1").equals("1"));
		assertTrue(converter.convertNumber("2").equals("10"));
		assertTrue(converter.convertNumber("31").equals("11111"));
	}
	
	@Test
	public void testNegatives() {
		NumericBinaryConverter converter = new NumericBinaryConverter();
		
		assertTrue(converter.convertNumber("-1").equals("-11"));
		assertTrue(converter.convertNumber("-2").equals("-110"));
		assertTrue(converter.convertNumber("-31").equals("-100001"));
	}

	@Test
	public void testFractional() {
		NumericBinaryConverter converter = new NumericBinaryConverter();
		
		assertTrue(converter.convertNumber("0,25").equals("0,01"));
		assertTrue(converter.convertNumber("2,").equals("10"));
		assertTrue(converter.convertNumber("31,24").equals("11111,00111101011100001010"));
	}
	
	@Test
	public void testNegativeFractional() {
		NumericBinaryConverter converter = new NumericBinaryConverter();
		
		assertTrue(converter.convertNumber("-0,4375").equals("-1,1001"));
		assertTrue(converter.convertNumber("-6,").equals("-1010"));
		assertTrue(converter.convertNumber("-6,25").equals("-1001,11"));
	}
}
