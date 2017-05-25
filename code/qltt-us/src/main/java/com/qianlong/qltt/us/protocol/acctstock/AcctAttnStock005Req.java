package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AcctAttnStock005Req extends Ttacct{
	
	private String stockcode;

	@NotNull
	@UTF8MAXLenth(max=32)
	@NotBlank
	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
}
