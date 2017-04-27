package com.qianlong.qltt.us.protocol;

import javax.validation.Valid;

public class PageReq {
	
	private PageReqParameter pagereq;

	@Valid
	public PageReqParameter getPagereq() {
		return pagereq;
	}

	public void setPagereq(PageReqParameter pagereq) {
		this.pagereq = pagereq;
	}
}
