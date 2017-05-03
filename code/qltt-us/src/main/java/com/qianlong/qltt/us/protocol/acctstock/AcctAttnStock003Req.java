package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;

import com.qianlong.qltt.us.protocol.Ttacct;

public class AcctAttnStock003Req extends Ttacct{
	@Valid
	private List<StockPool> stockpool;
	
	public List<StockPool> getStockpool() {
		return stockpool;
	}

	public void setStockpool(List<StockPool> stockpool) {
		this.stockpool = stockpool;
	}
}
