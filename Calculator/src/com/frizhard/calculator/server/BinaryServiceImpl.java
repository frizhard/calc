package com.frizhard.calculator.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.frizhard.calculator.client.BinaryService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class BinaryServiceImpl extends RemoteServiceServlet implements
		BinaryService {

	@Override
	public String convertToBinary(String number)
			throws IllegalArgumentException {
		BinaryConverter binarizer = new NumericBinaryConverter();
		String binaryNumber = binarizer.convertNumber(number);
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		PersistenceManager pm = pmf.getPersistenceManager();
		
		BinaryRequest immutableRequest = new BinaryRequest(number, binaryNumber);
		try {
			pm.makePersistent(immutableRequest);
		} finally {
			pm.close();
		}
		
		return binaryNumber;
	}

}
