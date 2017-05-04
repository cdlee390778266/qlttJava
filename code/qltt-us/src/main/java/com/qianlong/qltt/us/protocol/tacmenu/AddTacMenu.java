package com.qianlong.qltt.us.protocol.tacmenu;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class AddTacMenu extends Tactic{
	
	private String tacname;	//varchar2(64)		M		自定义组合指标名称	
	
	private String tacdetail;//varchar2(2000)		O		自定义组合指标描述	

	@NotNull
	@NotBlank
	@UTF8MAXLenth(max=64)
	public String getTacname() {
		return tacname;
	}

	public void setTacname(String tacname) {
		this.tacname = tacname;
	}

	@UTF8MAXLenth(max=2000)
	public String getTacdetail() {
		return tacdetail;
	}

	public void setTacdetail(String tacdetail) {
		this.tacdetail = tacdetail;
	}

	@Override
	public String toString() {
		return "tacname=" + tacname + ", tacdetail=" + tacdetail  + ",tactic=" + getTactic();
	}
}
