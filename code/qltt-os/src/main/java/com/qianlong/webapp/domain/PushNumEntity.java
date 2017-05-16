package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class PushNumEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "pushnum")
	private Integer pushNum;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public Integer getPushNum() {
		return pushNum;
	}

	public void setPushNum(Integer pushNum) {
		this.pushNum = pushNum;
	}
}
