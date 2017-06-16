package com.qianlong.qlttms.service.impl;

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

import com.qianlong.qlttms.http.IHttpClient;
import com.qianlong.qlttms.domain.MsDockConfig;
import com.qianlong.qlttms.domain.OAuthCallbackEntity;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.WechatMessage;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.service.IWechatCoreService;
import com.qianlong.qlttms.utils.Constants;


@Service("wechatCoreService")
public class WechatCoreServiceImpl implements IWechatCoreService {
	
	private Logger logger = LoggerFactory.getLogger(WechatCoreServiceImpl.class);
	
	@Autowired
	IHttpClient httpClient;

	@Override
	public OAuthCallbackEntity getOpenidByOAuth(MsDockConfig dockConfig, String code, String state) throws HttpRequestException {
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("appid", dockConfig.getWeixinAppid()));
		nvps.add(new BasicNameValuePair("secret", dockConfig.getWeixinAppsecret()));
		nvps.add(new BasicNameValuePair("code", code));
		nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
		
		HttpContent<OAuthCallbackEntity, WechatMessage> content = null;
		try {
			content = httpClient.httpGet(Constants.SCHEME_HTTPS, "api.weixin.qq.com", "/sns/oauth2/access_token", nvps, OAuthCallbackEntity.class, WechatMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("HTTP请求发生错误，请检查URI是否正确，网络是否畅通", e);
		}
		return content.getContent();
	}

}
