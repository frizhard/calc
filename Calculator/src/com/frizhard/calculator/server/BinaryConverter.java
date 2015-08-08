package com.frizhard.calculator.server;

/**
 * Interface necesario para convertir un número a binario.
 * 
 * Actualmente, solo se ha implementado la clase NumbericBinaryConverter, que convierte internamente
 * las cadenas a números, por lo que tiene precisión limitada. Para hacerlo correctamente, sería
 * necesario un conversor que trabajara directamente con las cadenas y no tuviera límites
 * 
 * @author jose
 *
 */
public interface BinaryConverter {
	public String convertNumber(String number);
}
