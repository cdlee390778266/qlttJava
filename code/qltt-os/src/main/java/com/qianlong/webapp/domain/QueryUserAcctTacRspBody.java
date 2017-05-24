package com.qianlong.webapp.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class QueryUserAcctTacRspBody {

	@JSONField(name = "pagersp")
	private UserServPageBean pageRsp;
	
	@JSONField(name = "tacmenu")
	private List<UserAcctTac> tacMenu;

	public UserServPageBean getPageRsp() {
		return pageRsp;
	}

	public void setPageRsp(UserServPageBean pageRsp) {
		this.pageRsp = pageRsp;
	}

	public List<UserAcctTac> getTacMenu() {
		return tacMenu;
	}

	public void setTacMenu(List<UserAcctTac> tacMenu) {
		this.tacMenu = tacMenu;
	}
}
