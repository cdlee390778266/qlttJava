package com.qianlong.qlttms.service;


import com.qianlong.qlttms.domain.BlockPage;
import com.qlcd.qltt.body.pvt.T02007002;
import com.qlcd.qltt.body.pvt.T02007004;



public interface IBlockService {
	
	
	/**
	 * <p>接口编号：IFC_StockLabel002
	 * <p>交易码：2007002
	 * <p>接口名称：标签个股查询[单标签]
	 * 
	 * @param weixinAccountid 微信ID
	 * @param pagereq	分页请求	
	 * @param label		标签代码	
	 * 
	 * @return 指定板块的个股清单
	 */
	public T02007002._rsp queryStockLabel(String weixinAccountid, String label, BlockPage page);
	
	
	
	/**
	 * <p>接口编号：IFC_StockLabel004
	 * <p>交易码：2007004
	 * <p>接口名称：标签个股查询[多标签-交集]
	 * 
	 * @param weixinAccountid 微信ID
	 * @param pagereq	分页请求	
	 * @param labelset		标签代码组	
	 * 
	 * @return 指定板块的个股清单
	 */
	public T02007004._rsp queryStockLimitLabel(String weixinAccountid, String[] labels, BlockPage page);
	
	
	
	
}
