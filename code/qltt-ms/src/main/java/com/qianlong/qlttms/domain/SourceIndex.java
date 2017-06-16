package com.qianlong.qlttms.domain;

public class SourceIndex {

	private String srcTactic;
	
	private Integer srcTacticPrm;
	
	public SourceIndex() {
		//
	}
	
	public SourceIndex(String srcTactic, Integer srcTacticPrm) {
		this.srcTactic = srcTactic;
		this.srcTacticPrm = srcTacticPrm;
	}

	public String getSrcTactic() {
		return srcTactic;
	}

	public void setSrcTactic(String srcTactic) {
		this.srcTactic = srcTactic;
	}

	public Integer getSrcTacticPrm() {
		return srcTacticPrm;
	}

	public void setSrcTacticPrm(Integer srcTacticPrm) {
		this.srcTacticPrm = srcTacticPrm;
	}
}
