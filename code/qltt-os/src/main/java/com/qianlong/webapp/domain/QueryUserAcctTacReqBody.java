package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryUserAcctTacReqBody {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "pagereq")
	private UserServPageBean pageReq;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public UserServPageBean getPageReq() {
		return pageReq;
	}

	public void setPageReq(UserServPageBean pageReq) {
		this.pageReq = pageReq;
	}
}
