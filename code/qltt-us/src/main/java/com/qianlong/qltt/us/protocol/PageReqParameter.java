package com.qianlong.qltt.us.protocol;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PageReqParameter {
	@NotNull
	@Min(0)
	private Integer reqstart;
	
	@NotNull
	@Min(1)
    private Integer reqnum;

	public Integer getReqstart() {
		return reqstart;
	}

	public void setReqstart(Integer reqstart) {
		this.reqstart = reqstart;
	}

	public Integer getReqnum() {
		return reqnum;
	}

	public void setReqnum(Integer reqnum) {
		this.reqnum = reqnum;
	}
}
