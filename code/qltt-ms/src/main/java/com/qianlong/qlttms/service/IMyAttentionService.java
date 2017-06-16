package com.qianlong.qlttms.service;

import java.util.List;

import com.qianlong.qlttms.domain.BaseIndex;

public interface IMyAttentionService {

	/**
	 * 新增账户关注指标
	 * @param weixinAccountId 该推推账号所属微信公众号ID
	 * @param index 指标
	 * @param ttacct 推推账号
	 */
	public void follow(String weixinAccountId,BaseIndex index, String ttacct);
	
	/**
	 * 账户取消指标关注
	 * @param weixinAccountId 该推推账号所属微信公众号ID
	 * @param index 指标
	 * @param ttacct 推推账号
	 */
	public void unfollow(String weixinAccountId,BaseIndex index, String ttacct);
	
	/**
	 * 账户关注指标变更
	 * @param weixinAccountId 该推推账号所属微信公众号ID
	 * @param indexList 指标列表
	 * @param ttacct 推推账号
	 */
	public void alteration(String weixinAccountId,List<BaseIndex> indexList, String ttacct);
	
	/**
	 * 账户关注指标查询
	 * @param weixinAccountId 该推推账号所属微信公众号ID
	 * @param ttacct 推推账号
	 * @return
	 */
	public List<BaseIndex> followingList(String weixinAccountId,String ttacct);
	
	/**
	 * 校验指标是否被当前用户关注
	 * @param weixinAccountId 该推推账号所属微信公众号ID
	 * @param index 指标
	 * @param ttacct 推推账号
	 * @return
	 */
	public boolean isFollow(String weixinAccountId,BaseIndex index, String ttacct);
}
