package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class VerifyFollowStatusEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "tactic")
	private String tacTic;
	
	@JSONField(name = "tacprm")
	private Integer tacPrm;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public String getTacTic() {
		return tacTic;
	}

	public void setTacTic(String tacTic) {
		this.tacTic = tacTic;
	}

	public Integer getTacPrm() {
		return tacPrm;
	}

	public void setTacPrm(Integer tacPrm) {
		this.tacPrm = tacPrm;
	}
}
