package com.qianlong.qltt.us.protocol.acctlogin;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctLogin002Req {
	@NotNull//非空
	private Integer svcchnl;
	
	@NotNull
	@UTF8MAXLenth(max=64)
	private String bindacct;
	
	public Integer getSvcchnl() {
		return svcchnl;
	}

	public void setSvcchnl(Integer svcchnl) {
		this.svcchnl = svcchnl;
	}

	public String getBindacct() {
		return bindacct;
	}

	public void setBindacct(String bindacct) {
		this.bindacct = bindacct;
	}

	public static void main(String[] args) {
		AcctLogin002Req req = new AcctLogin002Req();
		req.setSvcchnl(1);
		req.setBindacct("01234567890123456789012345678");
		System.out.println(JSONObject.fromObject(req).toString());
	}
}