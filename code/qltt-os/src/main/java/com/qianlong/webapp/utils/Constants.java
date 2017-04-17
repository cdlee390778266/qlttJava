package com.qianlong.webapp.utils;

public class Constants {

	public static final String SCHEME_HTTP = "http";
	
	public static final String SCHEME_HTTPS = "https";
	
	/**
	 * 获取access_token
	 */
	public static final String USER_SERV_OBTAINACCESSTOKEN_PATH = "/token/getAccessToken";
	
	/**
	 * 账号登录(第三方验证授权登录)
	 */
	public static final String USER_SERV_TDPART_AUTH_LOGIN = "/acctlogin/login002";
	
	/**
	 * 账号开通(手机号识别)
	 */
	public static final String USER_SERV_ACCT_OPEN = "/acctopen/open001";
	
	public static final String LOGIN_USER_ACCOUNT = "login.user.account";
}
