package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class PushScopeEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "pushscope")
	private Integer pushScope;
	
	@JSONField(name = "scopeprm")
	private Integer scopePrm;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public Integer getPushScope() {
		return pushScope;
	}

	public void setPushScope(Integer pushScope) {
		this.pushScope = pushScope;
	}

	public Integer getScopePrm() {
		return scopePrm;
	}

	public void setScopePrm(Integer scopePrm) {
		this.scopePrm = scopePrm;
	}
}
