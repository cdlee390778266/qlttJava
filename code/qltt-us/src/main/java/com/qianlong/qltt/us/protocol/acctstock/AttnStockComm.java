package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AttnStockComm {

	private String stockcode;
	
	private String stockname;

	@NotNull
	@UTF8MAXLenth(max=32)
	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	
	@NotNull
	@UTF8MAXLenth(max=128)
	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
}
