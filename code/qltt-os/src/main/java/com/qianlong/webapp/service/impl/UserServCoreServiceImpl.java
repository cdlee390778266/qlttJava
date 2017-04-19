package com.qianlong.webapp.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.domain.RegisterEntity;
import com.qianlong.webapp.domain.TrenchInfoEnity;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
import com.qianlong.webapp.domain.UserServMessage;
import com.qianlong.webapp.exception.HttpBusinessException;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.Constants;

@Service("userServCoreService")
public class UserServCoreServiceImpl extends CommonServiceImpl implements IUserServCoreService, CommonService {

	private Logger logger = LoggerFactory.getLogger(UserServCoreServiceImpl.class);

	@Autowired
	IHttpService httpService;

	@Override
	public UserServAccessToken obtainAccessToken(UserServAccessTokenReqBody body) throws HttpRequestException {
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("appid", body.getAppid()));

		HttpContent<UserServAccessToken, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")),
					Constants.USER_SERV_OBTAINACCESSTOKEN_PATH, nvps, (JSONObject) JSONObject.toJSON(body),
					UserServAccessToken.class, UserServMessage.class);

			executeSql("DELETE FROM user_serv_accesstoken");
			save(content.getContent());
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("HTTP请求发生错误，请检查URI是否正确，网络是否畅通", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new HttpBusinessException(content.getMessage().getErrorMsg());
		
		return content.getContent();
	}

	@Override
	public UserServAccessToken getCurrentAccessToken() {
		List<UserServAccessToken> list = getList(UserServAccessToken.class);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public AuthResultEntity tdPartAuthLogin(TrenchInfoEnity user) throws HttpRequestException {
		UserServAccessToken accessToken = getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));

		HttpContent<AuthResultEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")),
					Constants.USER_SERV_TDPART_AUTH_LOGIN, nvps, (JSONObject) JSONObject.toJSON(user),
					AuthResultEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("微信授权登录时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new HttpBusinessException(content.getMessage().getErrorMsg());
		
		return content.getContent();
	}

	@Override
	public AuthResultEntity userAcctOpen(RegisterEntity regInfo) throws HttpRequestException {
		UserServAccessToken accessToken = getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		HttpContent<AuthResultEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), Constants.USER_SERV_ACCT_OPEN,
					nvps, (JSONObject) JSONObject.toJSON(regInfo), AuthResultEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error(e.getMessage(), e);
			throw new HttpRequestException("开通用户账号时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new HttpBusinessException(content.getMessage().getErrorMsg());
		
		return content.getContent();
	}
}
