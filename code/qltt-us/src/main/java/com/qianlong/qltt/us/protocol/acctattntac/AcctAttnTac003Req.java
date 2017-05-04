package com.qianlong.qltt.us.protocol.acctattntac;

import java.util.List;

import javax.validation.Valid;

import com.qianlong.qltt.us.protocol.Ttacct;

public class AcctAttnTac003Req extends Ttacct{
	@Valid
	private List<AttnTacTic> attntactic;

	
	public List<AttnTacTic> getAttntactic() {
		return attntactic;
	}

	public void setAttntactic(List<AttnTacTic> attntactic) {
		this.attntactic = attntactic;
	}
}
