package com.qianlong.qlttms.service;

import com.qianlong.qlttms.domain.OAuthCallbackEntity;
import com.qianlong.qlttms.domain.db.WeixinAccount;
import com.qianlong.qlttms.exception.HttpRequestException;



public interface IWechatCoreService {

	/**
	 * 
	 * @param code code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。
	 * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	 * @return
	 */
	public OAuthCallbackEntity getOpenidByOAuth(WeixinAccount weixinAccount, String code, String state) throws HttpRequestException;
}
