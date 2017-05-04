package com.qianlong.qltt.us.protocol.acctattntac;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;

import net.sf.json.JSONObject;

public class AcctAttnTac002Req extends Ttacct{
	
	@NotNull
	private AttnTacTic cncltactic;

	@Valid
	public AttnTacTic getCncltactic() {
		return cncltactic;
	}

	public void setCncltactic(AttnTacTic cncltactic) {
		this.cncltactic = cncltactic;
	}
	
	public static void main(String[] args) {
		AcctAttnTac002Req req = new AcctAttnTac002Req();
		req.setTtacct("tt000000000001");

		AttnTacTic attn1 = new AttnTacTic();
		attn1.setTactic("指标1");
		attn1.setTacprm(1);
		req.setCncltactic(attn1);
		
		System.out.println(JSONObject.fromObject(req).toString());
	}
}
