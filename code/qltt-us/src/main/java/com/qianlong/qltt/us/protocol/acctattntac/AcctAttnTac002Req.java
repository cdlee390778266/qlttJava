package com.qianlong.qltt.us.protocol.acctattntac;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AcctAttnTac002Req {

	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;
	
	@NotEmpty
	@NotNull
	private List<AttnTacTic> cncltactic;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	@Valid
	public List<AttnTacTic> getCncltactic() {
		return cncltactic;
	}

	public void setCncltactic(List<AttnTacTic> cncltactic) {
		this.cncltactic = cncltactic;
	}
}
