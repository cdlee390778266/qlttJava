package com.qianlong.qltt.us.protocol;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class Ttacct {
	
	private String ttacct;

	@NotNull
	@UTF8MAXLenth(max=16)
	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}
}
