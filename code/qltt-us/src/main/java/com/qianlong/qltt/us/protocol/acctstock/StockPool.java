package com.qianlong.qltt.us.protocol.acctstock;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockPool {
	@NotNull
	@Max(3)
	@Min(1)
	private Integer poolindex;
	
	@Valid
	private List<AttnStock> attnstock;
	
	@Valid
	private List<CnclStock> cnclstock;

	public Integer getPoolindex() {
		return poolindex;
	}

	public void setPoolindex(Integer poolindex) {
		this.poolindex = poolindex;
	}

	public List<AttnStock> getAttnstock() {
		return attnstock;
	}

	public void setAttnstock(List<AttnStock> attnstock) {
		this.attnstock = attnstock;
	}

	public List<CnclStock> getCnclstock() {
		return cnclstock;
	}

	public void setCnclstock(List<CnclStock> cnclstock) {
		this.cnclstock = cnclstock;
	}
}
