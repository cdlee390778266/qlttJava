package com.qianlong.qlttms.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class UserFollowAlterationEntity {

	@JSONField(name = "ttacct")
	private String ttacct;
	
	@JSONField(name = "attntactic")
	private List<BaseIndex> attnTacTic;

	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}

	public List<BaseIndex> getAttnTacTic() {
		return attnTacTic;
	}

	public void setAttnTacTic(List<BaseIndex> attnTacTic) {
		this.attnTacTic = attnTacTic;
	}
}
