package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AttnStock001 extends AttnStockComm{
	@NotNull
	@NotEmpty
	private List<StockPoolIndex> stockpool;

	@Valid
	public List<StockPoolIndex> getStockpool() {
		return stockpool;
	}

	public void setStockpool(List<StockPoolIndex> stockpool) {
		this.stockpool = stockpool;
	}
}
