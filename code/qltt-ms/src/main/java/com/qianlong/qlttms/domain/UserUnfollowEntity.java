package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UserUnfollowEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "cncltactic")
	private BaseIndex index;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public BaseIndex getIndex() {
		return index;
	}

	public void setIndex(BaseIndex index) {
		this.index = index;
	}
}
