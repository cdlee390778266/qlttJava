package com.qianlong.qlttms.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryFollowStockRspEntity {

	@JSONField(name = "pagersp")
	private UserServPageBean pageRsp;
	
	@JSONField(name = "attnstock")
	private List<Stock> attnStock;

	public UserServPageBean getPageRsp() {
		return pageRsp;
	}

	public void setPageRsp(UserServPageBean pageRsp) {
		this.pageRsp = pageRsp;
	}

	public List<Stock> getAttnStock() {
		return attnStock;
	}

	public void setAttnStock(List<Stock> attnStock) {
		this.attnStock = attnStock;
	}
}
