package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("binary")
public interface BinaryService extends RemoteService {
	String convertToBinary(String number) throws IllegalArgumentException;
}
