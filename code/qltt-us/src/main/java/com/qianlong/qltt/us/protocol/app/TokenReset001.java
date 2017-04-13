package com.qianlong.qltt.us.protocol.app;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class TokenReset001 {

	@NotNull
	private String appid;	
	
	@NotNull
	private String old_token;
	
	@NotNull
	@UTF8MAXLenth(max=1024)
	private String new_token;
	
	@NotNull
	private String secret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getOld_token() {
		return old_token;
	}

	public void setOld_token(String old_token) {
		this.old_token = old_token;
	}

	public String getNew_token() {
		return new_token;
	}

	public void setNew_token(String new_token) {
		this.new_token = new_token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}

