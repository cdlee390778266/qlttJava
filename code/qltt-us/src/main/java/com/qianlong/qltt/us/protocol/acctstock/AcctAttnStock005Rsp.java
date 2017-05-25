package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

public class AcctAttnStock005Rsp {
	
	private List<StockPoolIndex> existpool;

	public List<StockPoolIndex> getExistpool() {
		return existpool;
	}

	public void setExistpool(List<StockPoolIndex> existpool) {
		this.existpool = existpool;
	}
}
