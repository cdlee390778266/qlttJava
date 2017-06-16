package com.qianlong.qlttms.utils;

public class UserServPath {
    
    public static final String USER_SERV_CONTEXT = "USER_SERV_CONTEXT";

	/**
	 * 获取access_token
	 */
	public static final String USER_SERV_OBTAINACCESSTOKEN_PATH = USER_SERV_CONTEXT + "/token/getAccessToken";
	
	/**
	 * 账号登录(第三方验证授权登录)
	 */
	public static final String USER_SERV_TDPART_AUTH_LOGIN = USER_SERV_CONTEXT + "/acctlogin/login002";
	
	/**
	 * 账号开通(手机号识别)
	 */
	public static final String USER_SERV_ACCT_OPEN = USER_SERV_CONTEXT + "/acctopen/open001";
	
	/**
	 * 新增账户关注指标
	 */
	public static final String USER_SERV_ACCT_ATTN_ADD = USER_SERV_CONTEXT + "/acctattntac/attntac001";
	
	/**
	 * 账户取消指标关注
	 */
	public static final String USER_SERV_ACCT_ATTN_CANCEL = USER_SERV_CONTEXT + "/acctattntac/attntac002";
	
	/**
	 * 账户关注指标变更
	 */
	public static final String USER_SERV_ACCT_ATTN_ALTER = USER_SERV_CONTEXT + "/acctattntac/attntac003";
	
	/**
	 * 账户关注指标查询
	 */
	public static final String USER_SERV_ACCT_ATTN_QUERY = USER_SERV_CONTEXT + "/acctattntac/attntac004";
	
	/**
	 * 校验账户关注指标查询
	 */
	public static final String USER_SERV_ACCT_ATTN_VERIFY = USER_SERV_CONTEXT + "/acctattntac/attntac005";
	
	/**
	 * 新增账户关注个股
	 */
	public static final String USER_SERV_STOCK_ATTN_ADD = USER_SERV_CONTEXT + "/acctattnstock/attnstock001";
	
	/**
	 * 取消账户个股关注
	 */
	public static final String USER_SERV_STOCK_ATTN_CANCEL = USER_SERV_CONTEXT + "/acctattnstock/attnstock002";
	
	/**
	 * 账户关注个股变更
	 */
	public static final String USER_SERV_STOCK_ATTN_ALTER = USER_SERV_CONTEXT + "/acctattnstock/attnstock003";
	
	/**
	 * 账户指定指标池关注个股查询
	 */
	public static final String USER_SERV_STOCK_ATTN_QUERY = USER_SERV_CONTEXT + "/acctattnstock/attnstock004";
	
	/**
	 * 账户关注个股查询
	 */
	public static final String USER_SERV_STOCK_ONE_ATTN_QUERY = USER_SERV_CONTEXT + "/acctattnstock/attnstock005";
	
	/**
	 * 全局推送范围设置
	 */
	public static final String USER_SERV_PUSH_SCOPE_SET = USER_SERV_CONTEXT + "/acctsetprm/setprm001";
	
	/**
	 * 全局推送范围查询
	 */
	public static final String USER_SERV_PUSH_SCOPE_QUERY = USER_SERV_CONTEXT + "/acctsetprm/setprm002";
	
	/**
	 * 全局推送数量设置
	 */
	public static final String USER_SERV_PUSH_NUM_SET = USER_SERV_CONTEXT + "/acctsetprm/setprm003";
	
	/**
	 * 全局推送数量查询
	 */
	public static final String USER_SERV_PUSH_NUM_QUERY = USER_SERV_CONTEXT + "/acctsetprm/setprm004";
	
	/**
	 * 全局推送频率设置
	 */
	public static final String USER_SERV_PUSH_FREQ_SET = USER_SERV_CONTEXT + "/acctsetprm/setprm005";
	
	/**
	 * 全局推送频率查询
	 */
	public static final String USER_SERV_PUSH_FREQ_QUERY = USER_SERV_CONTEXT + "/acctsetprm/setprm006";
	
	/**
	 * 新增账户组合指标菜单
	 */
	public static final String USER_SERV_ACCT_TAC_ADD = USER_SERV_CONTEXT + "/accttacmenu/tacmenu001";
	
	/**
	 * 删除账户组合指标菜单
	 */
	public static final String USER_SERV_ACCT_TAC_DEL = USER_SERV_CONTEXT + "/accttacmenu/tacmenu002";
	
	/**
	 * 账户组合指标菜单变更
	 */
	public static final String USER_SERV_ACCT_TAC_ALT = USER_SERV_CONTEXT + "/accttacmenu/tacmenu003";
	
	/**
	 * 账户组合指标菜单查询
	 */
	public static final String USER_SERV_ACCT_TAC_QUERY = USER_SERV_CONTEXT + "/accttacmenu/tacmenu004";
}
