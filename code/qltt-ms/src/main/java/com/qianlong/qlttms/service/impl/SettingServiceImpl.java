package com.qianlong.qlttms.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.PushFreqEntity;
import com.qianlong.qlttms.domain.PushNumEntity;
import com.qianlong.qlttms.domain.PushScopeEntity;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.ISettingService;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;

@Service
public class SettingServiceImpl extends UserServCommServiceImpl implements ISettingService {
	
	private Logger logger = Logger.getLogger(SettingServiceImpl.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public void setPushScope(String weixinAccountID,String ttacct, Integer pushScope, Integer scopePrm) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushScopeEntity entity = new PushScopeEntity();
		entity.setTtacct(ttacct);
		entity.setPushScope(pushScope);
		entity.setScopePrm(scopePrm);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP,accessToken, UserServPath.USER_SERV_PUSH_SCOPE_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送范围时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("设置全局推送范围错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void setPushNum(String weixinAccountID,String ttacct, Integer pushNum) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushNumEntity entity = new PushNumEntity();
		entity.setTtacct(ttacct);
		entity.setPushNum(pushNum);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_PUSH_NUM_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送数量时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("设置全局推送数量错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void setPushFreq(String weixinAccountID,String ttacct, Integer pushFreq) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushFreqEntity entity = new PushFreqEntity();
		entity.setTtacct(ttacct);
		entity.setPushFreq(pushFreq);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_PUSH_FREQ_SET,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("设置全局推送频率时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("设置全局推送频率错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public PushScopeEntity getPushScope(String weixinAccountID,String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushScopeEntity entity = new PushScopeEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushScopeEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP,accessToken, UserServPath.USER_SERV_PUSH_SCOPE_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushScopeEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送范围时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("获取全局推送范围错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}

	@Override
	public PushNumEntity getPushNum(String weixinAccountID,String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushNumEntity entity = new PushNumEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushNumEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP,accessToken, UserServPath.USER_SERV_PUSH_NUM_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushNumEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送数量时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("获取全局推送数量错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}

	@Override
	public PushFreqEntity getPushFreq(String weixinAccountID,String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountID);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		PushFreqEntity entity = new PushFreqEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<PushFreqEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_PUSH_FREQ_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), PushFreqEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取全局推送频率时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			throw new UserServBusinessException(
					String.format("获取全局推送频率错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}

}
