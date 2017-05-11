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
import com.qianlong.webapp.domain.FollowStockAlterationEntity;
import com.qianlong.webapp.domain.FollowStockEntity;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.domain.QueryFollowStockEntity;
import com.qianlong.webapp.domain.QueryFollowStockRspEntity;
import com.qianlong.webapp.domain.UnfollowStockEntity;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServMessage;
import com.qianlong.webapp.domain.UserServPageBean;
import com.qianlong.webapp.domain.UserStockContent;
import com.qianlong.webapp.domain.UserStockPool;
import com.qianlong.webapp.exception.UserServBusinessException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.service.IUserStockService;
import com.qianlong.webapp.utils.Constants;
import com.qianlong.webapp.utils.UserServPath;

@Service
public class UserStockServiceImpl implements IUserStockService {
	
	private Logger logger = Logger.getLogger(UserStockServiceImpl.class);
	
	@Autowired
	private IHttpService httpService;
	
	@Autowired
	private IUserServCoreService userServCoreService;

	@Override
	public void follow(UserStockContent userStock, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		FollowStockEntity entity = new FollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setContent(userStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_STOCK_ATTN_ADD,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("将指定股票加入选股池时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("将指定股票加入选股池时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void unfollow(UserStockContent userStock, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UnfollowStockEntity entity = new UnfollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setContent(userStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_STOCK_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("将指定股票移出选股池时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("将指定股票移出选股池时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void alteration(List<UserStockPool> attnStock, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		FollowStockAlterationEntity entity = new FollowStockAlterationEntity();
		entity.setTtacct(ttacct);
		entity.setStockPool(attnStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_STOCK_ATTN_ALTER,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("选股池股票变更时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("选股池股票变更时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public QueryFollowStockRspEntity followingList(UserServPageBean page, Integer poolIndex, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		QueryFollowStockEntity entity = new QueryFollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setPoolIndex(poolIndex);
		entity.setPageReq(page);
		
		HttpContent<QueryFollowStockRspEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_STOCK_ATTN_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), QueryFollowStockRspEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取选股池股票时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("获取选股池股票时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent();
	}

}
