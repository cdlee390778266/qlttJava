package com.qianlong.webapp.service;

import com.qianlong.webapp.domain.OAuthCallbackEntity;
import com.qianlong.webapp.exception.HttpRequestException;

import weixin.guanjia.account.entity.WeixinAccountEntity;

public interface IWechatCoreService {

	/**
	 * 
	 * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public OAuthCallbackEntity getOpenidByOAuth(WeixinAccountEntity account, String code, String state) throws HttpRequestException;
}
