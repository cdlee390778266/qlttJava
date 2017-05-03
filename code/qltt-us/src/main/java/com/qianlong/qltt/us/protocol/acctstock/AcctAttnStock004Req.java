package com.qianlong.qltt.us.protocol.acctstock;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.PageReqParameter;
import com.qianlong.qltt.us.protocol.TtacctPageReq;

import net.sf.json.JSONObject;

public class AcctAttnStock004Req extends TtacctPageReq{
	
	@NotNull
	@Max(3)
	@Min(1)
	private Integer poolindex;

	public Integer getPoolindex() {
		return poolindex;
	}

	public void setPoolindex(Integer poolindex) {
		this.poolindex = poolindex;
	}
	
	public static void main(String[] args) {
		AcctAttnStock004Req req = new AcctAttnStock004Req();
		req.setTtacct("tt000001");
		req.setPoolindex(1);
		PageReqParameter pageReqParameter = new PageReqParameter();
		pageReqParameter.setReqstart(0);
		pageReqParameter.setReqnum(10);
		req.setPagereq(pageReqParameter);
		
		System.out.println(JSONObject.fromObject(req));
	}
}
