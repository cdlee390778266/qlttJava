package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockPoolIndex {
	private Integer poolindex;

	@NotNull
	@Max(3)
	@Min(1)
	public Integer getPoolindex() {
		return poolindex;
	}

	public void setPoolindex(Integer poolindex) {
		this.poolindex = poolindex;
	}
}
