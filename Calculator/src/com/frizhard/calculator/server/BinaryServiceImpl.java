package com.frizhard.calculator.server;

import com.frizhard.calculator.client.BinaryService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
@SuppressWarnings("serial")
public class BinaryServiceImpl extends RemoteServiceServlet implements
		BinaryService {

	@Override
	public String convertToBinary(String number)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return "01110011";
	}

}
