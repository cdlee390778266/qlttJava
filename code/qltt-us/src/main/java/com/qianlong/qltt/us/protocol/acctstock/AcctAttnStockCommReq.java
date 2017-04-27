package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AcctAttnStockCommReq {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;
	
	@Valid
	private List<StockPool> stockpool;
	
	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public List<StockPool> getStockpool() {
		return stockpool;
	}

	public void setStockpool(List<StockPool> stockpool) {
		this.stockpool = stockpool;
	}
}
