package com.qianlong.qltt.us.protocol;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PageReq {
	
	private PageReqParameter pagereq;

	@Valid
	@NotNull
	public PageReqParameter getPagereq() {
		return pagereq;
	}

	public void setPagereq(PageReqParameter pagereq) {
		this.pagereq = pagereq;
	}
}
