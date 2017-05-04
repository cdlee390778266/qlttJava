package com.qianlong.qltt.us.protocol;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.qianlong.qltt.us.util.JSONUtil;
import com.qianlong.qltt.us.util.validator.UTF8MAXLenth;

public class TtacctPageReq extends PageReq{
	
	private String ttacct;

	@NotNull
	@UTF8MAXLenth(max=16)
	@NotBlank
	public String getTtacct() {
		return ttacct;
	}

	public void setTtacct(String ttacct) {
		this.ttacct = ttacct;
	}
	
	public static void main(String[] args) {
		TtacctPageReq pageReq = new TtacctPageReq();
		pageReq.setTtacct("tt000001");
		
		PageReqParameter pageReqParameter = new PageReqParameter();
		pageReqParameter.setReqnum(10);
		pageReqParameter.setReqstart(0);
		pageReq.setPagereq(pageReqParameter);
		System.out.println(JSONUtil.objToJson(pageReq));
	}
}
