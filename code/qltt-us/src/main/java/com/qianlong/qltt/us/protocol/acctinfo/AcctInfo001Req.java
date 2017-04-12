package com.qianlong.qltt.us.protocol.acctinfo;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctInfo001Req {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String  ttacct;//推推账号
	
	@UTF8MAXLenth(max=64)
	private String name	;//账号名称

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		AcctInfo001Req req = new AcctInfo001Req();
		req.setTtacct("qltt00000001");
		req.setName("AHQ");
		System.out.println(JSONObject.fromObject(req).toString());
	}
}
