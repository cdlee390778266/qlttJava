package com.qianlong.webapp.utils;

import org.jeecgframework.core.util.ResourceUtil;

public class UserServPath {

	/**
	 * 获取access_token
	 */
	public static final String USER_SERV_OBTAINACCESSTOKEN_PATH = ResourceUtil.getConfigByName("user.serv.context") + "token/getAccessToken";
	
	/**
	 * 账号登录(第三方验证授权登录)
	 */
	public static final String USER_SERV_TDPART_AUTH_LOGIN = ResourceUtil.getConfigByName("user.serv.context") + "acctlogin/login002";
	
	/**
	 * 账号开通(手机号识别)
	 */
	public static final String USER_SERV_ACCT_OPEN = ResourceUtil.getConfigByName("user.serv.context") + "acctopen/open001";
	
	/**
	 * 新增账户关注指标
	 */
	public static final String USER_SERV_ACCT_ATTN_ADD = ResourceUtil.getConfigByName("user.serv.context") + "acctattntac/attntac001";
	
	/**
	 * 账户取消指标关注
	 */
	public static final String USER_SERV_ACCT_ATTN_CANCEL = ResourceUtil.getConfigByName("user.serv.context") + "acctattntac/attntac002";
	
	/**
	 * 账户关注指标变更
	 */
	public static final String USER_SERV_ACCT_ATTN_ALTER = ResourceUtil.getConfigByName("user.serv.context") + "acctattntac/attntac003";
	
	/**
	 * 账户关注指标查询
	 */
	public static final String USER_SERV_ACCT_ATTN_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctattntac/attntac004";
	
	/**
	 * 校验账户关注指标查询
	 */
	public static final String USER_SERV_ACCT_ATTN_VERIFY = ResourceUtil.getConfigByName("user.serv.context") + "acctattntac/attntac005";
	
	/**
	 * 新增账户关注个股
	 */
	public static final String USER_SERV_STOCK_ATTN_ADD = ResourceUtil.getConfigByName("user.serv.context") + "acctattnstock/attnstock001";
	
	/**
	 * 取消账户个股关注
	 */
	public static final String USER_SERV_STOCK_ATTN_CANCEL = ResourceUtil.getConfigByName("user.serv.context") + "acctattnstock/attnstock002";
	
	/**
	 * 账户关注个股变更
	 */
	public static final String USER_SERV_STOCK_ATTN_ALTER = ResourceUtil.getConfigByName("user.serv.context") + "acctattnstock/attnstock003";
	
	/**
	 * 账户指定指标池关注个股查询
	 */
	public static final String USER_SERV_STOCK_ATTN_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctattnstock/attnstock004";
	
	/**
	 * 账户关注个股查询
	 */
	public static final String USER_SERV_STOCK_ONE_ATTN_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctattnstock/attnstock005";
	
	/**
	 * 全局推送范围设置
	 */
	public static final String USER_SERV_PUSH_SCOPE_SET = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm001";
	
	/**
	 * 全局推送范围查询
	 */
	public static final String USER_SERV_PUSH_SCOPE_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm002";
	
	/**
	 * 全局推送数量设置
	 */
	public static final String USER_SERV_PUSH_NUM_SET = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm003";
	
	/**
	 * 全局推送数量查询
	 */
	public static final String USER_SERV_PUSH_NUM_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm004";
	
	/**
	 * 全局推送频率设置
	 */
	public static final String USER_SERV_PUSH_FREQ_SET = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm005";
	
	/**
	 * 全局推送频率查询
	 */
	public static final String USER_SERV_PUSH_FREQ_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "acctsetprm/setprm006";
	
	/**
	 * 新增账户组合指标菜单
	 */
	public static final String USER_SERV_ACCT_TAC_ADD = ResourceUtil.getConfigByName("user.serv.context") + "accttacmenu/tacmenu001";
	
	/**
	 * 删除账户组合指标菜单
	 */
	public static final String USER_SERV_ACCT_TAC_DEL = ResourceUtil.getConfigByName("user.serv.context") + "accttacmenu/tacmenu002";
	
	/**
	 * 账户组合指标菜单变更
	 */
	public static final String USER_SERV_ACCT_TAC_ALT = ResourceUtil.getConfigByName("user.serv.context") + "accttacmenu/tacmenu003";
	
	/**
	 * 账户组合指标菜单查询
	 */
	public static final String USER_SERV_ACCT_TAC_QUERY = ResourceUtil.getConfigByName("user.serv.context") + "accttacmenu/tacmenu004";
}
