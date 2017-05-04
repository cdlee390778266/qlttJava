package com.qianlong.qltt.us.protocol.acctattntac;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;

import net.sf.json.JSONObject;

public class AcctAttnTac001Req extends Ttacct{
	@NotNull
	private AttnTacTic attntactic;

	@Valid
	public AttnTacTic getAttntactic() {
		return attntactic;
	}

	public void setAttntactic(AttnTacTic attntactic) {
		this.attntactic = attntactic;
	}
	
	
	public static void main(String[] args) {
		AcctAttnTac001Req req = new AcctAttnTac001Req();
		req.setTtacct("tt000000000001");

		AttnTacTic attn1 = new AttnTacTic();
		attn1.setTactic("指标1");
		attn1.setTacprm(1);
		req.setAttntactic(attn1);
		
		System.out.println(JSONObject.fromObject(req).toString());
	}
}
