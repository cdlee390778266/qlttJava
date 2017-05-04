package com.qianlong.qltt.us.protocol.acctattntac;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AttnTacTic{
	@NotNull
	@UTF8MAXLenth(max=16)
	@NotBlank
	private String tactic;//指标	
	
	@NotNull
	private Integer tacprm;//指标参数

	public String getTactic() {
		return tactic;
	}

	public void setTactic(String tactic) {
		this.tactic = tactic;
	}

	public Integer getTacprm() {
		return tacprm;
	}

	public void setTacprm(Integer tacprm) {
		this.tacprm = tacprm;
	}
}
