package com.qianlong.webapp.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.domain.PushFreqEntity;
import com.qianlong.webapp.domain.PushNumEntity;
import com.qianlong.webapp.domain.PushScopeEntity;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServMessage;
import com.qianlong.webapp.exception.UserServBusinessException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.ISettingService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.Constants;
import com.qianlong.webapp.utils.UserServPath;

@Service
public class SettingServiceImpl implements ISettingService {
	
	private Logger logger = Logger.getLogger(SettingServiceImpl.class);

	@Autowired
	private IHttpService httpService;
	
	@Autowired
	private IUserServCoreService userServCoreService;

	@Override
	public void setPushScope(String ttacct, Integer pushScope, Integer scopePrm) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushScopeEntity entity = new PushScopeEntity();
		entity.setTtacct(ttacct);
		entity.setPushScope(pushScope);
		entity.setScopePrm(scopePrm);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_SCOPE_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送范围时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("设置全局推送范围错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void setPushNum(String ttacct, Integer pushNum) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushNumEntity entity = new PushNumEntity();
		entity.setTtacct(ttacct);
		entity.setPushNum(pushNum);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_NUM_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送数量时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("设置全局推送数量错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void setPushFreq(String ttacct, Integer pushFreq) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushFreqEntity entity = new PushFreqEntity();
		entity.setTtacct(ttacct);
		entity.setPushFreq(pushFreq);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_FREQ_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送频率时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("设置全局推送频率错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public PushScopeEntity getPushScope(String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushScopeEntity entity = new PushScopeEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushScopeEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_SCOPE_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushScopeEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送范围时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("获取全局推送范围错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent();
	}

	@Override
	public PushNumEntity getPushNum(String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushNumEntity entity = new PushNumEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushNumEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_NUM_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushNumEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送数量时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("获取全局推送数量错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent();
	}

	@Override
	public PushFreqEntity getPushFreq(String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushFreqEntity entity = new PushFreqEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushFreqEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_PUSH_FREQ_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushFreqEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送频率时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("获取全局推送频率错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent();
	}

}
