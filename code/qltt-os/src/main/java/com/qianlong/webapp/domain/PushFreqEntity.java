package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class PushFreqEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "pushfreq")
	private Integer pushFreq;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public Integer getPushFreq() {
		return pushFreq;
	}

	public void setPushFreq(Integer pushFreq) {
		this.pushFreq = pushFreq;
	}
}
