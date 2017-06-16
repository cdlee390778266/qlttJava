package com.qianlong.qlttms.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class FollowStockAlterationEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "stockpool")
	private List<UserStockPool> stockPool;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public List<UserStockPool> getStockPool() {
		return stockPool;
	}

	public void setStockPool(List<UserStockPool> stockPool) {
		this.stockPool = stockPool;
	}
}
