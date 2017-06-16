package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UserServAccessTokenReqBody {

	@JSONField
	private String appid;
	
	@JSONField
	private String plaintext;
	
	@JSONField
	private String secret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
