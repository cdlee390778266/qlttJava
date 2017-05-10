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
import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.BaseIndex;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.domain.UserFollowAlterationEntity;
import com.qianlong.webapp.domain.UserFollowEntity;
import com.qianlong.webapp.domain.UserFollowIndicesEntity;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServMessage;
import com.qianlong.webapp.domain.UserUnfollowEntity;
import com.qianlong.webapp.exception.UserServBusinessException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IMyAttentionService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.Constants;

@Service
public class MyAttentionServiceImpl implements IMyAttentionService {

	private Logger logger = Logger.getLogger(MyAttentionServiceImpl.class);

	@Autowired
	private IHttpService httpService;

	@Autowired
	private IUserServCoreService userServCoreService;

	@Override
	public void follow(BaseIndex index, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserFollowEntity entity = new UserFollowEntity();
		entity.setIndex(index);
		entity.setTtacct(ttacct);
		HttpContent<Object, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), Constants.USER_SERV_ACCT_ATTN_ADD,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void unfollow(BaseIndex index, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserUnfollowEntity entity = new UserUnfollowEntity();
		entity.setIndex(index);
		entity.setTtacct(ttacct);
		HttpContent<Object, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), Constants.USER_SERV_ACCT_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("取消关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("取消关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void alteration(List<BaseIndex> indexList, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		UserFollowAlterationEntity entity = new UserFollowAlterationEntity();
		entity.setTtacct(ttacct);
		entity.setAttnTacTic(indexList);
		HttpContent<Object, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), Constants.USER_SERV_ACCT_ATTN_CANCEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("变更关注指标时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("变更关注指标错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public List<BaseIndex> followingList(String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		AuthResultEntity entity = new AuthResultEntity();
		entity.setTtacct(ttacct);
		
		HttpContent<UserFollowIndicesEntity, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), Constants.USER_SERV_ACCT_ATTN_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), UserFollowIndicesEntity.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("获取账户关注列表时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("获取账户关注列表错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent().getAttnTacTic();
	}

}
