package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseIndex {

	@JSONField(name = "tactic")
	private String tacTic;
	
	@JSONField(name = "tacname")
	private String tacName;
	
	@JSONField(name = "tacprm")
	private Integer tacPrm;
	
	@JSONField(name = "tacprmname")
	private String tacPrmName;

	public String getTacTic() {
		return tacTic;
	}

	public void setTacTic(String tacTic) {
		this.tacTic = tacTic;
	}

	public String getTacName() {
		return tacName;
	}

	public void setTacName(String tacName) {
		this.tacName = tacName;
	}

	public Integer getTacPrm() {
		return tacPrm;
	}

	public void setTacPrm(Integer tacPrm) {
		this.tacPrm = tacPrm;
	}

	public String getTacPrmName() {
		return tacPrmName;
	}

	public void setTacPrmName(String tacPrmName) {
		this.tacPrmName = tacPrmName;
	}

	@Override
	public String toString() {
		return "BaseIndex [tacTic=" + tacTic + ", tacName=" + tacName + "]";
	}
}
