package com.frizhard.calculator.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RecordFetchServiceAsync {
	void fetchRecords(AsyncCallback<String> callback);
}
