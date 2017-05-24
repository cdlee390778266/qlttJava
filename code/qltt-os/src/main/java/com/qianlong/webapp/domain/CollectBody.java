package com.qianlong.webapp.domain;

import java.util.List;

public class CollectBody {

	private UserAcctTac addTacMenu;
	
	private List<SourceIndex> indices;

	public UserAcctTac getAddTacMenu() {
		return addTacMenu;
	}

	public void setAddTacMenu(UserAcctTac addTacMenu) {
		this.addTacMenu = addTacMenu;
	}

	public List<SourceIndex> getIndices() {
		return indices;
	}

	public void setIndices(List<SourceIndex> indices) {
		this.indices = indices;
	}
}
