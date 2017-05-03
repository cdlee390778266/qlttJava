package com.qianlong.qltt.us.protocol.acctstock;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.util.JSONUtil;

public class AcctAttnStock001Req extends Ttacct{

	@NotNull
	private AttnStock001 attnstock;

	@Valid
	public AttnStock001 getAttnstock() {
		return attnstock;
	}

	public void setAttnstock(AttnStock001 attnstock) {
		this.attnstock = attnstock;
	}
	
	public static void main(String[] args) {
		AcctAttnStock001Req req = new AcctAttnStock001Req();
		req.setTtacct("tt000000000001");
		
		AttnStock001 stock001 = new AttnStock001();
		req.setAttnstock(stock001);
		stock001.setStockcode("60001");
		stock001.setStockname("xxx公司");
		
		List<StockPoolIndex> poolindex = new ArrayList<StockPoolIndex>();
		stock001.setStockpool(poolindex);
		StockPoolIndex stockPoolIndex = new StockPoolIndex();
		stockPoolIndex.setPoolindex(1);
		poolindex.add(stockPoolIndex);
		
		System.out.println(JSONUtil.objToJson(req));
	}
}
