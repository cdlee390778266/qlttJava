package com.qianlong.webapp.service;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.RegisterEntity;
import com.qianlong.webapp.domain.TrenchInfoEnity;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
import com.qianlong.webapp.exception.HttpRequestException;

public interface IUserServCoreService {

	/**
	 * 获取用户服务器访问token
	 * 
	 * @param appId 渠道ID
	 * @param plainText 签名明文
	 * @param secret 签名
	 * @return UserServAccessToken
	 */
	public UserServAccessToken obtainAccessToken(UserServAccessTokenReqBody body) throws HttpRequestException;
	
	/**
	 * 获取当前有效token
	 * 
	 * @return UserServAccessToken
	 */
	public UserServAccessToken getCurrentAccessToken();
	
	/**
	 * 账号登录(第三方验证授权登录)
	 * @param user
	 * @return
	 */
	public AuthResultEntity tdPartAuthLogin(TrenchInfoEnity user) throws HttpRequestException;
	
	/**
	 * 账号开通(手机号识别)
	 * @param regInfo
	 * @return
	 * @throws HttpRequestException
	 */
	public AuthResultEntity userAcctOpen(RegisterEntity regInfo) throws HttpRequestException;
}
