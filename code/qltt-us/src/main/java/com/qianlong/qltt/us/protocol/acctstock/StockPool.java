package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;

public class StockPool extends StockPoolIndex{
	
	@Valid
	private List<AttnStock003> attnstock;
	
	public List<AttnStock003> getAttnstock() {
		return attnstock;
	}

	public void setAttnstock(List<AttnStock003> attnstock) {
		this.attnstock = attnstock;
	}
}
