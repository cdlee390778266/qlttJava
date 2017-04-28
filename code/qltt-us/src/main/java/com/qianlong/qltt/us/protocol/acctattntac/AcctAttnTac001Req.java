package com.qianlong.qltt.us.protocol.acctattntac;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctAttnTac001Req {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;
	
	@NotNull
	@NotEmpty
	private List<AttnTacTic> attntactic;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	@Valid
	public List<AttnTacTic> getAttntactic() {
		return attntactic;
	}

	public void setAttntactic(List<AttnTacTic> attntactic) {
		this.attntactic = attntactic;
	}
	
	public static void main(String[] args) {
		AcctAttnTac001Req req = new AcctAttnTac001Req();
		req.setTtacct("tt000000000001");
		
		List<AttnTacTic> attntactic = new ArrayList<AttnTacTic>();
		req.setAttntactic(attntactic);
		AttnTacTic attn1 = new AttnTacTic();
		attn1.setTactic("指标1");
		attn1.setTacprm(1);
		attntactic.add(attn1);
		
		AttnTacTic attn2 = new AttnTacTic();
		attn2.setTactic("指标2");
		attn2.setTacprm(2);
		attntactic.add(attn2);
		
		AttnTacTic attn3= new AttnTacTic();
		attn3.setTactic("指标3");
		attn3.setTacprm(3);
		attntactic.add(attn3);
		
		System.out.println(JSONObject.fromObject(req).toString());
	}
}
