package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BinaryServiceAsync {
	void convertToBinary(String number, AsyncCallback<String> callback);
}
