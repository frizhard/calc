package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Obtiene todos los registros del DataStore
 * 
 * @author jose
 */

@RemoteServiceRelativePath("fetch")
public interface RecordFetchService extends RemoteService {
	String fetchRecords();
}
