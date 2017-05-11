package com.qianlong.webapp.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class UserStockContent {

	@JSONField(name = "stockcode")
	private String stockCode;
	
	@JSONField(name = "stockname")
	private String stockName;
	
	@JSONField(name = "stockpool")
	private List<PoolIndex> stockPool;

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public List<PoolIndex> getStockPool() {
		return stockPool;
	}

	public void setStockPool(List<PoolIndex> stockPool) {
		this.stockPool = stockPool;
	}
}
