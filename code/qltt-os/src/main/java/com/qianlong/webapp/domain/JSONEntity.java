package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class JSONEntity {

	@JSONField(name = "status")
	private Integer status;
	
	@JSONField(name = "message")
	private String message;
	
	@JSONField(name = "content")
	private Object content;
	
	public JSONEntity() {
		//
	}
	
	public JSONEntity(Integer status, String message, Object content) {
		this.status = status;
		this.message = message;
		this.content = content;
	}

	/**
	 * 获取状态status, 1:success, 0:failure
	 * @return 状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态status, 1:success, 0:failure
	 * @param status 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
}
