package com.qianlong.webapp.domain;

public class UserServMessage {

	private String errorCode;
	
	private String errorMsg;
	
	public UserServMessage() {
		//
	}
	
	public UserServMessage(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "UserServMessage [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
	}
}
