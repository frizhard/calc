package com.frizhard.calculator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Interface asíncrona para RecordFetchService
 * 
 * @author jose
 */
public interface RecordFetchServiceAsync {
	void fetchRecords(AsyncCallback<String> callback);
}
