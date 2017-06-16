package com.qianlong.qlttms.domain;

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
	
	/**
	 * 当前用户所登陆的微信公众号
	 */
	private String weixinAccountId;
	
	/**
	 * 当前用户所登陆的微信公众号的appid
	 */
	private String weixinAccountAppid;

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

	
	public String getWeixinAccountId() {
		return weixinAccountId;
	}

	public void setWeixinAccountId(String weixinAccountId) {
		this.weixinAccountId = weixinAccountId;
	}

	public String getWeixinAccountAppid() {
		return weixinAccountAppid;
	}

	public void setWeixinAccountAppid(String weixinAccountAppid) {
		this.weixinAccountAppid = weixinAccountAppid;
	}

	@Override
	public String toString() {
		return "AuthResultEntity [cn=" + cn + ", ttacct=" + ttacct
				+ ", weixinAccountId=" + weixinAccountId
				+ ", weixinAccountAppid=" + weixinAccountAppid + "]";
	}
}
