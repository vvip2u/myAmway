package com.myAmway.dao.exception;

public class DataOperationException extends Exception {
	
	private static final long serialVersionUID = 3642012843096341774L;

	public DataOperationException(String message) {
		super(message);
	}
	
	public DataOperationException(Throwable cause) {
		super(cause);
	}
	
	public DataOperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
