package com.qianlong.qltt.us.protocol.acctattntac;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AcctAttnTac003Req {

	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;
	
	@Valid
	private List<AttnTacTic> attntactic;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public List<AttnTacTic> getAttntactic() {
		return attntactic;
	}

	public void setAttntactic(List<AttnTacTic> attntactic) {
		this.attntactic = attntactic;
	}
}
