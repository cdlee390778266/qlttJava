package com.qianlong.qltt.us.domain.comm;

import net.sf.json.JSONObject;

public class CommRsp {
	
	private String errorCode = "0";
	
	private String errorMsg = "OK";
	
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
	
	public static void main(String[] args) {
		CommRsp rsp = new CommRsp();
		rsp.setErrorCode("0");
		rsp.setErrorMsg("ok");
		System.out.println(JSONObject.fromObject(rsp).toString());
	}
}
