package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class RegisterEntity {

	@JSONField(name = "cn")
	private String cn;
	
	/**
	 * 渠道账号绑定信息
	 */
	@JSONField(name = "acctbindinfo")
	private TrenchInfoEnity acctBindInfo;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public TrenchInfoEnity getAcctBindInfo() {
		return acctBindInfo;
	}

	public void setAcctBindInfo(TrenchInfoEnity acctBindInfo) {
		this.acctBindInfo = acctBindInfo;
	}
}
