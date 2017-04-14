package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class OAuthCallbackEntity {

	@JSONField(name = "access_token")
	private String accessToken;
	
	@JSONField(name = "expires_in")
	private Integer expiresIn;
	
	@JSONField(name = "refresh_token")
	private String refreshToken;
	
	@JSONField(name = "openid")
	private String openId;
	
	@JSONField(name = "scope")
	private String scope;
	
	@JSONField(name = "unionid")
	private String unionId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Override
	public String toString() {
		return "OAuthCallbackEntity [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", refreshToken="
				+ refreshToken + ", openId=" + openId + ", scope=" + scope + ", unionId=" + unionId + "]";
	}
}
