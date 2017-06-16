package com.qianlong.qlttms.domain;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class UserFollowIndicesEntity {

	@JSONField(name = "attntactic")
	private List<BaseIndex> attnTacTic;

	public List<BaseIndex> getAttnTacTic() {
		return attnTacTic;
	}

	public void setAttnTacTic(List<BaseIndex> attnTacTic) {
		this.attnTacTic = attnTacTic;
	}
}
