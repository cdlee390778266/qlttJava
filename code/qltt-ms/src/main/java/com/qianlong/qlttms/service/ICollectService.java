package com.qianlong.qlttms.service;

import java.util.List;

import com.qianlong.qlttms.domain.SourceIndex;
import com.qianlong.qlttms.domain.UserAcctTac;

/**
 * 收藏组合指标
 * @author wangk
 */
public interface ICollectService {

	/**
	 * 收藏组合指标
	 * 
	 * @param indices 原生指标列表
	 * @param addTacMenu 组合指标名称和备注
	 * @param ttacct 推推账号
	 */
	public void collect(String weixinAccountId,List<SourceIndex> indices, UserAcctTac addTacMenu, String ttacct);
	
	
}
