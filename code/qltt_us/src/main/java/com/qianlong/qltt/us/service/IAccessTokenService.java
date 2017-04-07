package com.qianlong.qltt.us.service;

import javax.servlet.ServletContext;

import com.qianlong.qltt.us.domain.token.AccessToken;
import com.qianlong.qltt.us.domain.token.GetAccessTokenReq;
import com.qianlong.qltt.us.domain.token.GetAccessTokenRsp;

public interface IAccessTokenService extends ICommService{
	/**
	 * 验证AccessToken是否合法
	 */
	void isAccessTokenLegal(ServletContext context, String accees_token, String uri);
	
	/**
	 * 检查该Token是否过期,传入的参数是以小时计算的
	 */
	boolean isTokenExpire(AccessToken token,Long time);

	/**
	 * 根据请求参数获取access_token
	 */
	GetAccessTokenRsp getAccessToken(ServletContext servletContext, GetAccessTokenReq req);
}
