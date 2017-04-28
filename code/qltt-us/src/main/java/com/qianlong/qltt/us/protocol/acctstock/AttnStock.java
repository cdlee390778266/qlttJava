package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AttnStock {
	@NotNull
	@Min(1)
	private Integer stockorder;
	
	@NotNull
	@UTF8MAXLenth(max=32)
	private String stockcode;
	
	@NotNull
	@UTF8MAXLenth(max=128)
	private String stockname;

	public Integer getStockorder() {
		return stockorder;
	}

	public void setStockorder(Integer stockorder) {
		this.stockorder = stockorder;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}
}
