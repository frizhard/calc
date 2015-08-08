package com.frizhard.calculator.client;

/**
 * Interface Aritmetic
 * 
 * Define la forma de operar con cadenas: Primer operando, Operador y Segundo operando.
 * La implementación posteriormente puede ser de diversas maneras.
 * 
 * En mi caso solo he implementado la clase DoubleAritmetic, que convierte las cadenas a double
 * y realiza la operación. En un futuro se implementaría la clase StringAritmetic, que trabajaría
 * directamente con cadenas para permitir longitudes arbitrarias y no tener problemas de redondeo.
 * 
 * @author jose
 */
public interface Aritmetic {
	public String operate(String operand1, String operator, String operand2);
}
