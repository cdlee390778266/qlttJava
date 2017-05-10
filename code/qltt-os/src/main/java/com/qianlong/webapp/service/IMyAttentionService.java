package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.BaseIndex;

public interface IMyAttentionService {

	/**
	 * 新增账户关注指标
	 * @param index 指标
	 * @param ttacct 推推账号
	 */
	public void follow(BaseIndex index, String ttacct);
	
	/**
	 * 账户取消指标关注
	 * @param index 指标
	 * @param ttacct 推推账号
	 */
	public void unfollow(BaseIndex index, String ttacct);
	
	/**
	 * 账户关注指标变更
	 * @param indexList 指标列表
	 * @param ttacct 推推账号
	 */
	public void alteration(List<BaseIndex> indexList, String ttacct);
	
	/**
	 * 账户关注指标查询
	 * @param ttacct 推推账号
	 * @return
	 */
	public List<BaseIndex> followingList(String ttacct);
}
