package com.qianlong.qltt.us.protocol.acctlogin;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctLogin001Req {
	@NotNull//非空
	@UTF8MAXLenth(max=16)//最大长度16
	private String cn;
	
	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public static void main(String[] args) {
		AcctLogin001Req req = new AcctLogin001Req();
		req.setCn("13730894567");
		
		System.out.println(JSONObject.fromObject(req).toString());
	}
}