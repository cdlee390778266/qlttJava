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
import com.qianlong.qlttms.domain.FollowStockAlterationEntity;
import com.qianlong.qlttms.domain.FollowStockEntity;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.QueryFollowStockEntity;
import com.qianlong.qlttms.domain.QueryFollowStockRspEntity;
import com.qianlong.qlttms.domain.QueryStockPoolIndexsEntity;
import com.qianlong.qlttms.domain.QueryStockPoolIndexsRspEntity;
import com.qianlong.qlttms.domain.UnfollowStockEntity;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.domain.UserServPageBean;
import com.qianlong.qlttms.domain.UserStockContent;
import com.qianlong.qlttms.domain.UserStockPool;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.service.IUserStockService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;

@Service
public class UserStockServiceImpl extends UserServCommServiceImpl implements IUserStockService {
	
	private Logger logger = Logger.getLogger(UserStockServiceImpl.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public void follow(String weixinAccountId, UserStockContent userStock, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		FollowStockEntity entity = new FollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setContent(userStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_STOCK_ATTN_ADD,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("将指定股票加入选股池时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("将指定股票加入选股池时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void unfollow(String weixinAccountId, UserStockContent userStock, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UnfollowStockEntity entity = new UnfollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setContent(userStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_STOCK_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("将指定股票移出选股池时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("将指定股票移出选股池时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void alteration(String weixinAccountId, List<UserStockPool> attnStock, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		FollowStockAlterationEntity entity = new FollowStockAlterationEntity();
		entity.setTtacct(ttacct);
		entity.setStockPool(attnStock);
		
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_STOCK_ATTN_ALTER,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("选股池股票变更时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("选股池股票变更时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public QueryFollowStockRspEntity followingList(String weixinAccountId, UserServPageBean page, Integer poolIndex, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		QueryFollowStockEntity entity = new QueryFollowStockEntity();
		entity.setTtacct(ttacct);
		entity.setPoolIndex(poolIndex);
		entity.setPageReq(page);
		
		HttpContent<QueryFollowStockRspEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_STOCK_ATTN_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), QueryFollowStockRspEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取选股池股票时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("获取选股池股票时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}
	
	
	@Override
	public QueryStockPoolIndexsRspEntity  queryStockPoolIndexs(String weixinAccountId, String stockcode, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		QueryStockPoolIndexsEntity entity = new QueryStockPoolIndexsEntity();
		entity.setTtacct(ttacct);
		entity.setStockCode(stockcode);
		
		HttpContent<QueryStockPoolIndexsRspEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_STOCK_ONE_ATTN_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), QueryStockPoolIndexsRspEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("账户关注个股查询由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("账户关注个股查询发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent();
	}
}
