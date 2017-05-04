package com.qianlong.qltt.us.protocol.acctinfo;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctInfo001Req extends Ttacct{
	@UTF8MAXLenth(max=64)
	private String name	;//账号名称

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
