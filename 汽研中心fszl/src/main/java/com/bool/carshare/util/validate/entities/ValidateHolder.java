package com.bool.carshare.util.validate.entities;

public class ValidateHolder {
	
	
	// filed value -> messageCode 
	private Object filedValue;

	private String messageCode;
	

	
	public ValidateHolder(Object filedValue, String messageCode) {
		super();
		this.filedValue = filedValue;
		this.messageCode = messageCode;
	}


	public String getMessageCode() {
		return messageCode;
	}


	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}


	public ValidateHolder() {
		super();
	}


	public Object getFiledValue() {
		return filedValue;
	}

	public void setFiledValue(Object filedValue) {
		this.filedValue = filedValue;
	}

	

	
}
