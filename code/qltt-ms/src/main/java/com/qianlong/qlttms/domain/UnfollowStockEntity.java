package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UnfollowStockEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "cnclstock")
	private UserStockContent content;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public UserStockContent getContent() {
		return content;
	}

	public void setContent(UserStockContent content) {
		this.content = content;
	}
}
