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
	
	/*
	 * 
	 * 
	 */
	private String keyid;
	
	/*
	 * 
	 * 
	 */
	private String openid;
	
	

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


	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		return "AuthResultEntity [cn=" + cn + ", ttacct=" + ttacct
				+ ", weixinAccountId=" + weixinAccountId
				+ ", weixinAccountAppid=" + weixinAccountAppid + ", keyid="
				+ keyid + ", openid=" + openid + "]";
	}


	
	
	
}
