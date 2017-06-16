package com.qianlong.qlttms.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2092567996969483080L;

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
