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
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.BaseIndex;
import com.qianlong.qlttms.domain.FollowStatusEntity;
import com.qianlong.qlttms.domain.HttpContent;
import com.qianlong.qlttms.domain.UserFollowAlterationEntity;
import com.qianlong.qlttms.domain.UserFollowEntity;
import com.qianlong.qlttms.domain.UserFollowIndicesEntity;
import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServMessage;
import com.qianlong.qlttms.domain.UserUnfollowEntity;
import com.qianlong.qlttms.domain.VerifyFollowStatusEntity;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.IMyAttentionService;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.utils.Constants;
import com.qianlong.qlttms.utils.UserServPath;

@Service
public class MyAttentionServiceImpl extends UserServCommServiceImpl implements IMyAttentionService {

	private Logger logger = Logger.getLogger(MyAttentionServiceImpl.class);

	@Autowired
	private IDockService dockService;

	@Override
	public void follow(String weixinAccountId,BaseIndex index, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserFollowEntity entity = new UserFollowEntity();
		entity.setIndex(index);
		entity.setTtacct(ttacct);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken,UserServPath.USER_SERV_ACCT_ATTN_ADD,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void unfollow(String weixinAccountId,BaseIndex index, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserUnfollowEntity entity = new UserUnfollowEntity();
		entity.setIndex(index);
		entity.setTtacct(ttacct);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_ACCT_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("取消关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("取消关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public void alteration(String weixinAccountId,List<BaseIndex> indexList, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserFollowAlterationEntity entity = new UserFollowAlterationEntity();
		entity.setTtacct(ttacct);
		entity.setAttnTacTic(indexList);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP,accessToken, UserServPath.USER_SERV_ACCT_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("变更关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("变更关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
	}

	@Override
	public List<BaseIndex> followingList(String weixinAccountId,String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		AuthResultEntity entity = new AuthResultEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<UserFollowIndicesEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_ACCT_ATTN_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), UserFollowIndicesEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取账户关注列表时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("获取账户关注列表错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent().getAttnTacTic();
	}

	@Override
	public boolean isFollow(String weixinAccountId,BaseIndex index, String ttacct) {
		UserServAccessToken accessToken = dockService.getCurrentAccessToken(weixinAccountId);
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		VerifyFollowStatusEntity entity = new VerifyFollowStatusEntity();
		entity.setTtacct(ttacct);
		entity.setTacTic(index.getTacTic());
		entity.setTacPrm(index.getTacPrm());
		
		HttpContent<FollowStatusEntity, UserServMessage> content = null;
		try {
			content = this.httpPost(Constants.SCHEME_HTTP, accessToken, UserServPath.USER_SERV_ACCT_ATTN_VERIFY,
					nvps, (JSONObject)JSONObject.toJSON(entity), FollowStatusEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("校验指标是否被当前用户关注时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode())){
			dockService.resetAccessTokenByAccountid(weixinAccountId, Long.parseLong(content.getMessage().getErrorCode())/10000000);
			throw new UserServBusinessException(
					String.format("校验指标是否被当前用户关注时发生错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		}
		return content.getContent().getIsAttn() == 1 ? Boolean.TRUE : Boolean.FALSE;
	}
}
