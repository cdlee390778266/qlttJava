package com.qianlong.webapp.utils;

import org.jeecgframework.core.util.ResourceUtil;

public class Constants {

	public static final String SCHEME_HTTP = "http";
	
	public static final String SCHEME_HTTPS = "https";
	
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
	
	public static final String LOGIN_USER_ACCOUNT = "login.user.account";
	
	public static final String USER_SERV_IDENTIFICATION_CODE = "errorCode";
	
	public static final String USER_SERV_IDENTIFICATION_MSG = "errorMsg";
	
	public static final String USER_SERV_CODE_OK = "0";
	
	public static final String WECHAT_IDENTIFICATION_CODE = "errcode";
	
	public static final String WECHAT_IDENTIFICATION_MSG = "errmsg";
}
