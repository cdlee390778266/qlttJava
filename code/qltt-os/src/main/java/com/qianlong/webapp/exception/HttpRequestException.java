package com.qianlong.webapp.exception;

public class HttpRequestException extends Exception {

	private static final long serialVersionUID = -5781926262030737979L;

	public HttpRequestException() {
		super();
	}

	public HttpRequestException(String message) {
		super(message);
	}

	public HttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
