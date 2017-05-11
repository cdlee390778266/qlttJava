package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Stock {

	@JSONField(name = "stockorder")
	private Integer stockOrder;
	
	@JSONField(name = "stockcode")
	private String stockCode;
	
	@JSONField(name = "stockname")
	private String stockName;

	public Integer getStockOrder() {
		return stockOrder;
	}

	public void setStockOrder(Integer stockOrder) {
		this.stockOrder = stockOrder;
	}

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
}
