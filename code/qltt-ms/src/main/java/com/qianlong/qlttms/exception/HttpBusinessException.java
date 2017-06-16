package com.qianlong.qlttms.exception;

/**
 * HTTP请求业务异常，包括微信业务和用户服务
 */
public class HttpBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -2821348743259591634L;

	public HttpBusinessException(String message) {
		super(message);
	}
	
	public HttpBusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
