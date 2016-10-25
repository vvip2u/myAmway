package com.myAmway.dao.exception;

public class NoDataFoundException extends DataOperationException {
	
	private static final long serialVersionUID = -4346635110319859152L;

	public NoDataFoundException(String message) {
		super(message);
	}
	
	public NoDataFoundException(Throwable cause) {
		super(cause);
	}
	
	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
