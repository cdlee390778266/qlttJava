package com.qianlong.qltt.us.protocol.acctattntac;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AcctAttnTac004Req {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}
}
