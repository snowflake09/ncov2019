package com.framework.exception;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 1561262255898670523L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
