package com.qianlong.webapp.service;

public interface IGetMessageInfoService {

	/**
	 * <p>交易码：2004001 对应3001001协议推送记录
	 * <p>接口名称：即时推送记录查询
	 * @param eventdate 事件日期
	 * @param eventno 事件编号
	 * @param ttacct 推推账号
	 * @param svcchnl 服务渠道
	 * @return 即时推送记录查询
	 */
	public String queryT3001001(String eventdate,Integer eventno,String ttacct,Integer svcchnl);
	
	/**
	 * <p>交易码：2004002 对应3001002协议推送记录
	 * <p>接口名称：即时推送记录查询
	 * @param eventdate 事件日期
	 * @param eventno 事件编号
	 * @param ttacct 推推账号
	 * @param svcchnl 服务渠道
	 * @return 即时推送记录查询
	 */
	public String queryT3001002(String eventdate,Integer eventno,String ttacct,Integer svcchnl);
	
}
