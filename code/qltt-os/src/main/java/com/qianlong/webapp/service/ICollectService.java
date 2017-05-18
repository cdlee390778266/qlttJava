package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.domain.UserAcctTac;

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
	public void collect(List<SourceIndex> indices, UserAcctTac addTacMenu, String ttacct);
	
	/**
	 * 关注组合指标
	 * 
	 * @param indices 原生指标列表
	 * @param ttacct 推推账号
	 */
	public void follow(List<SourceIndex> indices, String ttacct);
}
