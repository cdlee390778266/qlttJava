package com.qianlong.qlttms.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryStockPoolIndexsRspEntity {
	
	@JSONField(name = "existpool")
	private List<PoolIndex> existpools;

	public List<PoolIndex> getExistpools() {
		return existpools;
	}

	public void setExistpools(List<PoolIndex> existpools) {
		this.existpools = existpools;
	}
}
