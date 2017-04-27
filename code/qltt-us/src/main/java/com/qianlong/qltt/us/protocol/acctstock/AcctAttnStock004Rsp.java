package com.qianlong.qltt.us.protocol.acctstock;

import java.util.ArrayList;
import java.util.List;

import com.qianlong.qltt.us.protocol.PageRsp;
import com.qianlong.qltt.us.protocol.PageRspParameter;

import net.sf.json.JSONObject;

public class AcctAttnStock004Rsp extends PageRsp{
	
	private List<CnclStock> attnstock;

	public List<CnclStock> getAttnstock() {
		return attnstock;
	}

	public void setAttnstock(List<CnclStock> attnstock) {
		this.attnstock = attnstock;
	}
	
	public static void main(String[] args) {
		AcctAttnStock004Rsp rsp = new AcctAttnStock004Rsp();
		PageRspParameter pageRspParameter = new PageRspParameter();
		pageRspParameter.setRspnum(10);
		pageRspParameter.setTotalnum(29);
		rsp.setPagersp(pageRspParameter);
		
		List<CnclStock> tCnclStocks = new ArrayList<CnclStock>();
		CnclStock cnclStock = new CnclStock();
		cnclStock.setStockcode("6100000");
		cnclStock.setStockname("中国石化");
		tCnclStocks.add(cnclStock);
		
		cnclStock = new CnclStock();
		cnclStock.setStockcode("6100001");
		cnclStock.setStockname("中国石油");
		tCnclStocks.add(cnclStock);
		rsp.setAttnstock(tCnclStocks);
		
		System.out.println(JSONObject.fromObject(rsp));
	}
}
