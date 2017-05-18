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
import com.qianlong.webapp.domain.AddUserAcctTacReqBody;
import com.qianlong.webapp.domain.AltUserAcctTacReqBody;
import com.qianlong.webapp.domain.DelUserAcctTacReqBody;
import com.qianlong.webapp.domain.HttpContent;
import com.qianlong.webapp.domain.QueryUserAcctTacReqBody;
import com.qianlong.webapp.domain.QueryUserAcctTacRspBody;
import com.qianlong.webapp.domain.UserAcctTac;
import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServMessage;
import com.qianlong.webapp.domain.UserServPageBean;
import com.qianlong.webapp.exception.UserServBusinessException;
import com.qianlong.webapp.service.IHttpService;
import com.qianlong.webapp.service.IUserAcctTacMenuService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.Constants;
import com.qianlong.webapp.utils.UserServPath;

@Service
public class UserAcctTacMenuServiceImpl implements IUserAcctTacMenuService {
	
	private Logger logger = Logger.getLogger(UserAcctTacMenuServiceImpl.class);

	@Autowired
	private IHttpService httpService;
	
	@Autowired
	private IUserServCoreService userServCoreService;

	@Override
	public void add(UserAcctTac userAcctTac, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		AddUserAcctTacReqBody entity = new AddUserAcctTacReqBody();
		entity.setTtacct(ttacct);
		entity.setAddTacMenu(userAcctTac);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_ACCT_TAC_ADD,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("新增账户组合指标菜单时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("新增账户组合指标菜单错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void remove(UserAcctTac userAcctTac, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		DelUserAcctTacReqBody entity = new DelUserAcctTacReqBody();
		entity.setTtacct(ttacct);
		entity.setDelTacMenu(userAcctTac);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_ACCT_TAC_DEL,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("删除账户组合指标菜单时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("删除账户组合指标菜单错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public void alter(List<UserAcctTac> tacMenuList, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		AltUserAcctTacReqBody entity = new AltUserAcctTacReqBody();
		entity.setTtacct(ttacct);
		entity.setAddTacMenu(tacMenuList);
		HttpContent<?, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_ACCT_TAC_ALT,
					nvps, (JSONObject)JSONObject.toJSON(entity), null, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("账户组合指标菜单变更时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("账户组合指标菜单变更错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
	}

	@Override
	public QueryUserAcctTacRspBody query(UserServPageBean pageReq, String ttacct) {
		UserServAccessToken accessToken = userServCoreService.getCurrentAccessToken();
		List<NameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("access_token", accessToken.getAccessToken()));
		
		QueryUserAcctTacReqBody entity = new QueryUserAcctTacReqBody();
		entity.setTtacct(ttacct);
		entity.setPageReq(pageReq);
		HttpContent<QueryUserAcctTacRspBody, UserServMessage> content = null;
		try {
			content = httpService.httpPost(Constants.SCHEME_HTTP, ResourceUtil.getConfigByName("user.serv.host"),
					Integer.valueOf(ResourceUtil.getConfigByName("user.serv.port")), UserServPath.USER_SERV_ACCT_TAC_QUERY,
					nvps, (JSONObject)JSONObject.toJSON(entity), QueryUserAcctTacRspBody.class, UserServMessage.class);
		} catch (URISyntaxException | IOException e) {
			logger.error("账户组合指标菜单查询时由于网络原因导致失败", e);
		}
		
		if (content.getMessage() != null && !Constants.USER_SERV_CODE_OK.equals(content.getMessage().getErrorCode()))
			throw new UserServBusinessException(
					String.format("账户组合指标菜单查询错误 - 错误信息: %s", content.getMessage().getErrorMsg()),
					content.getMessage().getErrorCode());
		return content.getContent();
	}

}
