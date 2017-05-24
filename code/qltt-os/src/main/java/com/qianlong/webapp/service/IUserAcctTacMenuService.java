package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.QueryUserAcctTacRspBody;
import com.qianlong.webapp.domain.UserAcctTac;
import com.qianlong.webapp.domain.UserServPageBean;

/**
 * 账户组合指标菜单
 * @author wangk
 */
public interface IUserAcctTacMenuService {

	/**
	 * 新增账户组合指标菜单
	 * 
	 * @param userAcctTac 账户组合指标
	 * @param ttacct 推推账号
	 */
	public void add(UserAcctTac userAcctTac, String ttacct);
	
	/**
	 * 删除账户组合指标菜单
	 * 
	 * @param userAcctTac 账户组合指标
	 * @param ttacct 推推账号
	 */
	public void remove(UserAcctTac userAcctTac, String ttacct);
	
	/**
	 * 账户组合指标菜单变更
	 * 
	 * @param tacMenuList 组合指标列表
	 * @param ttacct 推推账号
	 */
	public void alter(List<UserAcctTac> tacMenuList, String ttacct);
	
	/**
	 * 账户组合指标菜单查询
	 * 
	 * @param pageReq 分页请求
	 * @param ttacct 推推账号
	 * @return
	 */
	public QueryUserAcctTacRspBody query(UserServPageBean pageReq, String ttacct);
}
