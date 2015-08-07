package com.frizhard.calculator.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.frizhard.calculator.client.RecordFetchService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RecordFetchServiceImpl extends RemoteServiceServlet implements
		RecordFetchService {

	public String fetchRecords() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("transactions-optional");
		PersistenceManager pm = pmf.getPersistenceManager();
		
		Query q = pm.newQuery(BinaryRequest.class);
		q.setOrdering("requestDate desc");
		
		String dev = null;
		
		try {
			List<BinaryRequest> results = (List<BinaryRequest>) q.execute();
			
			StringBuffer composedResult = new StringBuffer();
			for(BinaryRequest req : results) {
				String row = "Request date: " + req.getRequestDate() + ", requested number: " + req.getRequestedNumber() + ", converted number: " + req.getBinaryString();
				composedResult.append("<p>" + row + "</p>");
			}
			
			dev = composedResult.toString();
		} finally {
			q.closeAll();
		}
		
		return dev;
	}

}
