package com.qianlong.qltt.us.protocol.tacmenu;

import javax.validation.constraints.NotNull;

public class AddTacMenu003 extends AddTacMenu{
	@NotNull
	private Integer order;//integer M 顺序号			

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
