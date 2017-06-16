package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UserAcctTac {

	@JSONField(name = "tactic")
	private String tacTic;
	
	@JSONField(name = "tacname")
	private String tacName;
	
	@JSONField(name = "tacdetail")
	private String tacDetail;
	
	@JSONField(name = "order")
	private Integer order;

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

	public String getTacDetail() {
		return tacDetail;
	}

	public void setTacDetail(String tacDetail) {
		this.tacDetail = tacDetail;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
