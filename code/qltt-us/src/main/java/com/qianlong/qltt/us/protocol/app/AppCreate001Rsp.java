package com.qianlong.qltt.us.protocol.app;

import javax.validation.constraints.NotNull;

public class AppCreate001Rsp {

	@NotNull
	private String appid;	
	
	@NotNull
	private String token;
	
	@NotNull
	private String secret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}

