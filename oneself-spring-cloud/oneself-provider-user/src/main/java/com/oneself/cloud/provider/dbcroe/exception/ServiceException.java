package com.oneself.cloud.provider.dbcroe.exception;

public class ServiceException extends MisException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8705167646414506373L;

	public ServiceException() {
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
	
	public ServiceException(String code, String message, Throwable cause){
		super(code, message, cause);
	}

}
