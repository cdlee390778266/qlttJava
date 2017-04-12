package com.qianlong.qltt.us.protocol.acctinfo;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctInfo002Req {
	@NotNull
	@UTF8MAXLenth(max=16)
	private String ttacct;//推推账号	
	
	@NotNull
	@UTF8MAXLenth(max=64)
	private String	devno;//设备号	
	
	@NotNull
	private Integer devtype;//设备类型	

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public String getDevno() {
		return devno;
	}

	public void setDevno(String devno) {
		this.devno = devno;
	}

	public Integer getDevtype() {
		return devtype;
	}

	public void setDevtype(Integer devtype) {
		this.devtype = devtype;
	}
	
	public static void main(String[] args) {
		AcctInfo002Req req = new AcctInfo002Req();
		req.setDevno("XXXXXXXXXXXXXX");
		req.setDevtype(1);
		req.setTtacct("qltt00000001");
		System.out.println(JSONObject.fromObject(req).toString());
	}

}
