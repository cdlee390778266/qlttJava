package com.qianlong.qltt.us.protocol.tacmenu;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class Tactic {
	private String tactic;

	@NotNull
	@NotBlank
	@UTF8MAXLenth(max=16)
	public String getTactic() {
		return tactic;
	}

	public void setTactic(String tactic) {
		this.tactic = tactic;
	}
}
