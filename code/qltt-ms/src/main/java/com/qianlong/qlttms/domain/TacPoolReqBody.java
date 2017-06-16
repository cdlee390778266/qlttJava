package com.qianlong.qlttms.domain;

public class TacPoolReqBody extends PagingBean {

	private String tacTic;
	
	private Integer tacPrm;

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
