package com.frizhard.calculator.client;

/**
 * Interface para que una FSM de calculadora notifique de cambios en el número mostrado
 * 
 * @author jose
 *
 */
public interface CalculatorOutput {
	void outputDidChangeTo(String output);
}
