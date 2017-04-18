package com.qianlong.webapp.exception;

public class HttpBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -2821348743259591634L;

	public HttpBusinessException(String message) {
		super(message);
	}
	
	public HttpBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
