package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryFollowStockEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "poolindex")
	private Integer poolIndex;
	
	@JSONField(name = "pagereq")
	private UserServPageBean pageReq;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public Integer getPoolIndex() {
		return poolIndex;
	}

	public void setPoolIndex(Integer poolIndex) {
		this.poolIndex = poolIndex;
	}

	public UserServPageBean getPageReq() {
		return pageReq;
	}

	public void setPageReq(UserServPageBean pageReq) {
		this.pageReq = pageReq;
	}
}
