package com.qianlong.webapp.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.webapp.domain.OAuthCallbackEntity;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IWechatCoreService;
import com.qianlong.webapp.utils.Constants;

import weixin.guanjia.account.entity.WeixinAccountEntity;

@Service("wechatCoreService")
public class WechatCoreServiceImpl implements IWechatCoreService {
	
	private Logger logger = LoggerFactory.getLogger(WechatCoreServiceImpl.class);
	
	@Autowired
	IHttpService httpService;

	@Override
	public OAuthCallbackEntity getOpenidByOAuth(WeixinAccountEntity account, String code, String state) throws HttpRequestException {
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("appid", account.getAccountappid()));
		nvps.add(new BasicNameValuePair("secret", account.getAccountappsecret()));
		nvps.add(new BasicNameValuePair("code", code));
		nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
		
		OAuthCallbackEntity oauthCallbackEntity = null;
		try {
			oauthCallbackEntity = httpService.httpGet(Constants.SCHEME_HTTPS, "api.weixin.qq.com", "/sns/oauth2/access_token", nvps, OAuthCallbackEntity.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("HTTP请求发生错误，请检查URI是否正确，网络是否畅通", e);
		}
		return oauthCallbackEntity;
	}

}
