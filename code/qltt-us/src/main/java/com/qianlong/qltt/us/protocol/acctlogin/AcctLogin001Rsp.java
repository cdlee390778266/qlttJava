package com.qianlong.qltt.us.protocol.acctlogin;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctLogin001Rsp extends Ttacct{
	@NotNull
	@UTF8MAXLenth(max=16)
	private String cn;//手机号	


	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}
	
	public static void main(String[] args) {
		AcctLogin001Rsp rsp  = new AcctLogin001Rsp();
		rsp.setCn("13735263215");
		rsp.setTtacct("qltt00000001");
		System.out.println(JSONObject.fromObject(rsp).toString());
	}
}

