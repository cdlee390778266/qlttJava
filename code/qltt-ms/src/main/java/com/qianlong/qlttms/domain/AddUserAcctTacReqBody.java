package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class AddUserAcctTacReqBody {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "addtacmenu")
	private UserAcctTac addTacMenu;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public UserAcctTac getAddTacMenu() {
		return addTacMenu;
	}

	public void setAddTacMenu(UserAcctTac addTacMenu) {
		this.addTacMenu = addTacMenu;
	}
}
