package com.qianlong.qlttms.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class UserServPageBean {

	@JSONField(name = "reqnum")
	private Integer reqNum;
	
	@JSONField(name = "reqstart")
	private Integer reqStart;
	
	@JSONField(name = "rspnum")
	private Integer rspNum;
	
	@JSONField(name = "totalnum")
	private Integer totalNum;

	public Integer getReqNum() {
		return reqNum;
	}

	public void setReqNum(Integer reqNum) {
		this.reqNum = reqNum;
	}

	public Integer getReqStart() {
		return reqStart;
	}

	public void setReqStart(Integer reqStart) {
		this.reqStart = reqStart;
	}

	public Integer getRspNum() {
		return rspNum;
	}

	public void setRspNum(Integer rspNum) {
		this.rspNum = rspNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	
}
