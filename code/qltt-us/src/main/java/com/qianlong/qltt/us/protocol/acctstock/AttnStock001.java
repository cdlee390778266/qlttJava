package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;

public class AttnStock001 extends AttnStockComm{
	
	private List<StockPoolIndex> stockpool;

	@Valid
	public List<StockPoolIndex> getStockpool() {
		return stockpool;
	}

	public void setStockpool(List<StockPoolIndex> stockpool) {
		this.stockpool = stockpool;
	}
}
