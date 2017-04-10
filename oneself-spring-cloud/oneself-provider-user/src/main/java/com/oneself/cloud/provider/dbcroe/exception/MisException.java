package com.oneself.cloud.provider.dbcroe.exception;

public class MisException extends RuntimeException {
	protected String code;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4994215805002713908L;

	public MisException() {
	}

	public MisException(String message) {
		super(message);
	}

	public MisException(Throwable cause) {
		super(cause);
	}

	public MisException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MisException(String code, String message, Throwable cause){
		super(message, cause);
		this.code=code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
