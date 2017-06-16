package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryStockPoolIndexsEntity {
	
	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "stockcode")
	private String stockCode;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
}
