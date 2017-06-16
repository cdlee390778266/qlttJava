package com.qianlong.qlttms.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.RegisterEntity;
import com.qianlong.qlttms.domain.TrenchInfoEnity;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServAccessTokenReqBody;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;



@Service("userServCoreService")
@Transactional
public class UserServCoreServiceImpl extends UserServCommServiceImpl implements IUserServCoreService {

	private Logger logger = LoggerFactory.getLogger(UserServCoreServiceImpl.class);

	@Autowired
	private IDockService dockService;


	@Override
	public AuthResultEntity tdPartAuthLogin(String weixinAccountId,TrenchInfoEnity user) throws HttpRequestException {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));

		HttpContent<AuthResultEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken,
					UserServPath.USER_SERV_TDPART_AUTH_LOGIN, nvps, (JSONObject) JSONObject.toJSON(user),
					AuthResultEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("微信授权登录时由于网络原因导致失败", e);
		}

		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){			
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			
			throw new UserServBusinessException(
					String.format("微信授权登录错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
			
		}
		return content.getContent();
	}

	@Override
	public AuthResultEntity userAcctOpen(String weixinAccountId,RegisterEntity regInfo) throws HttpRequestException {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));

		HttpContent<AuthResultEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_ACCT_OPEN,
					nvps, (JSONObject) JSONObject.toJSON(regInfo), AuthResultEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("开通用户账号时由于网络原因导致失败", e);
		}

		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){		
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);			
			throw new UserServBusinessException(
					String.format("开通用户账号错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}
	
}
