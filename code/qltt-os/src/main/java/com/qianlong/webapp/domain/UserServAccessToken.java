package com.qianlong.webapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.jeecgframework.core.common.entity.IdEntity;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "user_serv_accesstoken")
public class UserServAccessToken extends IdEntity {

	@JSONField(name = "access_token")
	private String accessToken;
	
	@JSONField(name = "product_time")
	private Long productTime;
	
	@JSONField(name = "expires_in")
	private Integer expiresIn;

	@Column(name = "access_token")
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Column(name = "product_time")
	public Long getProductTime() {
		return productTime;
	}

	public void setProductTime(Long productTime) {
		this.productTime = productTime;
	}

	@Column(name = "expires_in")
	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
}
