package com.qianlong.webapp.service;

import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;

public interface IUserServCoreService {

	/**
	 * 获取用户服务器访问token
	 * 
	 * @param appId 渠道ID
	 * @param plainText 签名明文
	 * @param secret 签名
	 * @return UserServAccessToken
	 */
	public UserServAccessToken obtainAccessToken(UserServAccessTokenReqBody body);
	
	/**
	 * 获取当前有效token
	 * 
	 * @return UserServAccessToken
	 */
	public UserServAccessToken getCurrentAccessToken();
}
