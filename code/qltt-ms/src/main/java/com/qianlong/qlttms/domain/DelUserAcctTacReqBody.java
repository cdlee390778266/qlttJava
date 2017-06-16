package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class DelUserAcctTacReqBody {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "deltacmenu")
	private UserAcctTac delTacMenu;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public UserAcctTac getDelTacMenu() {
		return delTacMenu;
	}

	public void setDelTacMenu(UserAcctTac delTacMenu) {
		this.delTacMenu = delTacMenu;
	}
}
