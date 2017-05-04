package com.qianlong.webapp.exception;

/**
 * <p>用户服务器业务异常
 * <p>用户服务器返回完整的异常信息和错误码
 * 
 */
public class UserServBusinessException extends HttpBusinessException {

	private static final long serialVersionUID = 3344500583606871404L;
	
	private String errorCode;

	public UserServBusinessException(String message) {
		super(message);
	}
	
	public UserServBusinessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public UserServBusinessException(String message, String errorCode, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@Override
	public String getMessage() {
		return String.format("%s 错误码: %s", super.getMessage(), this.errorCode);
	}
}
