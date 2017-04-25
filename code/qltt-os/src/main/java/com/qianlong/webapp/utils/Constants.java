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
	
	public static final String LOGIN_USER_ACCOUNT = "login.user.account";
	
	public static final String USER_SERV_IDENTIFICATION_CODE = "errorCode";
	
	public static final String USER_SERV_IDENTIFICATION_MSG = "errorMsg";
	
	public static final String USER_SERV_CODE_OK = "1";
	
	public static final String WECHAT_IDENTIFICATION_CODE = "errcode";
	
	public static final String WECHAT_IDENTIFICATION_MSG = "errmsg";
}
