package com.qianlong.qlttms.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.http.IHttpClient;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.service.ICommonDBService;
import com.qianlong.qlttms.utils.UserServPath;

public class UserServCommServiceImpl extends CommonDBServiceImpl implements ICommonDBService{

	@Autowired
	private IHttpClient httpClient;

	public <T, E> HttpContent<T, E> httpPost(String scheme,
			UserServAccessToken userServAccessToken, String path,
			List<NameValuePair> nvps, JSONObject json, Class<T> content,
			Class<E> message)
			throws URISyntaxException, ClientProtocolException, IOException {
		//如果用户端口为空，设置为默认的-1
		int port = StringUtils.isEmpty(userServAccessToken.getUserServPort())
				? -1
				: Integer.parseInt(userServAccessToken.getUserServPort());
		
		return httpClient.httpPost(scheme,
				userServAccessToken.getUserServHost(), port,
				path.replace(UserServPath.USER_SERV_CONTEXT,
						userServAccessToken.getUserServContext()),
				nvps, json, content, message);
	};

}
