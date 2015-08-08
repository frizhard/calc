package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("fetch")
public interface RecordFetchService extends RemoteService {
	String fetchRecords();
}
