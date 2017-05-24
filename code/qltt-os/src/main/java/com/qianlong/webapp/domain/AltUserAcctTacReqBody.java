package com.qianlong.webapp.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class AltUserAcctTacReqBody {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "addtacmenu")
	private List<UserAcctTac> addTacMenu;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public List<UserAcctTac> getAddTacMenu() {
		return addTacMenu;
	}

	public void setAddTacMenu(List<UserAcctTac> addTacMenu) {
		this.addTacMenu = addTacMenu;
	}
}
