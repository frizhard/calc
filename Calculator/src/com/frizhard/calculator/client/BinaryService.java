package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 * 
 * Convierte a binario la cadena que se le pasa por parámetro. Si no es una cadena válida,
 * devuelve el caracter de error Constants.Error
 */
@RemoteServiceRelativePath("binary")
public interface BinaryService extends RemoteService {
	String convertToBinary(String number) throws IllegalArgumentException;
}
