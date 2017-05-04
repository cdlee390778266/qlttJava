package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AttnStock003 extends AttnStockComm{
	@NotNull
	@Min(1)
	private Integer stockorder;
	
	public Integer getStockorder() {
		return stockorder;
	}

	public void setStockorder(Integer stockorder) {
		this.stockorder = stockorder;
	}
}
