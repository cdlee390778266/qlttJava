package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class PoolIndex {

	@JSONField(name = "poolindex")
	private Integer poolIndex;

	public Integer getPoolIndex() {
		return poolIndex;
	}

	public void setPoolIndex(Integer poolIndex) {
		this.poolIndex = poolIndex;
	}
}
