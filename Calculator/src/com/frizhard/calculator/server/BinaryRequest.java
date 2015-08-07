package com.frizhard.calculator.server;

import com.google.appengine.api.datastore.Key;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class BinaryRequest {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Date requestDate;
	
	@Persistent
	private String requestedNumber;
	
	@Persistent
	private String binaryString;
	
	public BinaryRequest(String requestedNumber, String binaryString) {
		this.requestedNumber = requestedNumber;
		this.binaryString = binaryString;
		this.requestDate = new Date();
	}
	
	public Date getRequestDate() {
		return requestDate;
	}
	
	public String getRequestedNumber() {
		return requestedNumber;
	}
	
	public String getBinaryString() {
		return binaryString;
	}
}
