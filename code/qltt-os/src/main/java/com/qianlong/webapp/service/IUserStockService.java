package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.QueryFollowStockRspEntity;
import com.qianlong.webapp.domain.QueryStockPoolIndexsRspEntity;
import com.qianlong.webapp.domain.UserServPageBean;
import com.qianlong.webapp.domain.UserStockContent;
import com.qianlong.webapp.domain.UserStockPool;

public interface IUserStockService {

	/**
	 * 将指定股票加入指定的选股池
	 * 
	 * @param userStock 股票和选股池信息
	 * @param ttacct 推推账号
	 */
	public void follow(UserStockContent userStock, String ttacct);
	
	/**
	 * 将指定股票移出指定的选股池
	 * 
	 * @param userStock 股票和选股池信息
	 * @param ttacct 推推账号
	 */
	public void unfollow(UserStockContent userStock, String ttacct);
	
	/**
	 * 账户关注个股变更
	 * 
	 * @param attnStock 股票和选股池信息
	 * @param ttacct 推推账号
	 */
	public void alteration(List<UserStockPool> attnStock, String ttacct);
	
	/**
	 * 账户指定指标池关注个股查询
	 * 
	 * @param page 分页信息
	 * @param poolIndex 选股池
	 * @param ttacct 推推账号
	 * @return 选股池分页数据
	 */
	public QueryFollowStockRspEntity followingList(UserServPageBean page, Integer poolIndex,String ttacct);
	
	/**
	 * 账户关注个股查询
	 *
	 * @param ttacct 推推账号
	 * @param stockcode 股票代码
	 * @return 当前推推账号下当前股票所属选股池
	 */
	public QueryStockPoolIndexsRspEntity  queryStockPoolIndexs(String stockcode, String ttacct);
}
