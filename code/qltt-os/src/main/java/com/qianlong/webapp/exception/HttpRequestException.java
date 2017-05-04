package com.qianlong.webapp.exception;

/**
 * HTTP请求异常，网络异常
 */
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
