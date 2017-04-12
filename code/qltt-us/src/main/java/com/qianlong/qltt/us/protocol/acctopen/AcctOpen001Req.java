package com.qianlong.qltt.us.protocol.acctopen;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctOpen001Req {
	@NotNull//非空
	@UTF8MAXLenth(max=16)//最大长度16
	private String cn;
	
	private AcctBindInfo acctbindinfo;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	@Valid
	public AcctBindInfo getAcctbindinfo() {
		return acctbindinfo;
	}

	public void setAcctbindinfo(AcctBindInfo acctbindinfo) {
		this.acctbindinfo = acctbindinfo;
	}
	
	public static void main(String[] args) {
		AcctOpen001Req req = new AcctOpen001Req();
		req.setCn("13730894567");
		
		AcctBindInfo bindInfo = new AcctBindInfo();
		bindInfo.setSvcchnl(1);
		bindInfo.setBindacct("1234567890123456789012345678");
		req.setAcctbindinfo(bindInfo);
		
		System.out.println(JSONObject.fromObject(req).toString());
	}
}