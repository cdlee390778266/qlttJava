package com.qianlong.qltt.us.protocol.acctopen;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctOpen001Rsp {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;//推推账号	
	
	@NotNull
	@UTF8MAXLenth(max=16)
	private String cn;//手机号	

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}
	
	public static void main(String[] args) {
		AcctOpen001Rsp rsp  = new AcctOpen001Rsp();
		rsp.setCn("13735263215");
		rsp.setTtacct("qltt00000001");
		System.out.println(JSONObject.fromObject(rsp).toString());
	}
}

