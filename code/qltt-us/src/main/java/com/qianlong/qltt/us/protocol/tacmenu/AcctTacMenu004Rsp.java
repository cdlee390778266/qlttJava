package com.qianlong.qltt.us.protocol.tacmenu;

import java.util.List;

import com.qianlong.qltt.us.protocol.PageRsp;

public class AcctTacMenu004Rsp extends PageRsp{
	
	private List<AddTacMenu> tacmenu;

	public List<AddTacMenu> getTacmenu() {
		return tacmenu;
	}

	public void setTacmenu(List<AddTacMenu> tacmenu) {
		this.tacmenu = tacmenu;
	}
}
