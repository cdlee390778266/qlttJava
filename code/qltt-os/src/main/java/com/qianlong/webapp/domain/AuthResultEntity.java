package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class AuthResultEntity {

	/**
	 * 手机号码
	 */
	@JSONField(name = "cn")
	private String cn;
	
	/**
	 * 推推账号
	 */
	@JSONField(name = "ttacct")
	private String ttacct;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	@Override
	public String toString() {
		return "LoginResultEntity [cn=" + cn + ", ttacct=" + ttacct + "]";
	}
}
