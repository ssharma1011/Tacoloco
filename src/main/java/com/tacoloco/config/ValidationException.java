package com.tacoloco.config;

public class ValidationException extends RuntimeException {

	private String exceptionReason;

	public ValidationException(String exceptionReason) {
		super();
		this.exceptionReason = exceptionReason;
	}

	public String getExceptionReason() {
		return exceptionReason;
	}

	public void setExceptionReason(String exceptionReason) {
		this.exceptionReason = exceptionReason;
	}
	
	
	
}
