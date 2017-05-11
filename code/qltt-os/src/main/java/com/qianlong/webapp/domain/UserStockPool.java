package com.qianlong.webapp.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class UserStockPool {

	@JSONField(name = "poolindex")
	private Integer poolIndex;
	
	@JSONField(name = "attnstock")
	private List<Stock> attnStock;

	public Integer getPoolIndex() {
		return poolIndex;
	}

	public void setPoolIndex(Integer poolIndex) {
		this.poolIndex = poolIndex;
	}

	public List<Stock> getAttnStock() {
		return attnStock;
	}

	public void setAttnStock(List<Stock> attnStock) {
		this.attnStock = attnStock;
	}
}
