package com.qianlong.qltt.us.protocol.acctopen;

import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

import net.sf.json.JSONObject;

public class AcctBindInfo {
	@NotNull
	private Integer svcchnl;

	@NotNull
	@UTF8MAXLenth(max = 64)
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
		AcctBindInfo bindInfo = new AcctBindInfo();
		bindInfo.setSvcchnl(1);
		bindInfo.setBindacct("1234567890123456789012345678");

		System.out.println(bindInfo.getBindacct());

		System.out.println(JSONObject.fromObject(bindInfo).toString());
	}
}
