package com.qianlong.webapp.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class FollowStatusEntity {

	@JSONField(name = "isattn")
	private Integer isAttn;

	public Integer getIsAttn() {
		return isAttn;
	}

	public void setIsAttn(Integer isAttn) {
		this.isAttn = isAttn;
	}
}
