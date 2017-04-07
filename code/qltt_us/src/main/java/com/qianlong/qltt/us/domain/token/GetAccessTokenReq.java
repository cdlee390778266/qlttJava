package com.qianlong.qltt.us.domain.token;

import javax.validation.constraints.NotNull;
/**
 * 获取AccessToken的请求类
 */
public class GetAccessTokenReq {
	
	@NotNull(message = "appid不能为空")
	private String appid;
	
	@NotNull(message = "plaintext不能为空")
	private String plaintext;
	
	@NotNull(message = "secret不能为空")
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
