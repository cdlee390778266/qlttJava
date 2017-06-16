package com.qianlong.qlttms.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.qlttms.dao.ICommonDao;
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.MsDockConfig;
import com.qianlong.qlttms.domain.OAuthCallbackEntity;
import com.qianlong.qlttms.domain.TrenchInfoEnity;
import com.qianlong.qlttms.exception.BusinessException;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.exception.UserServBusinessException;
import com.qianlong.qlttms.service.ISubscriberService;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.service.IWechatCoreService;


@Service
public class SubscriberServiceImpl implements ISubscriberService {
	
	private Logger logger = Logger.getLogger(SubscriberServiceImpl.class);
	
	@Autowired
	private IUserServCoreService userServCoreService;
	
	@Autowired
	private IWechatCoreService wechatCoreService;
	
	@Autowired
	public ICommonDao commonDao;


	@Override
	public AuthResultEntity wechatUserLogin(String code, String state, HttpSession session) {
		AuthResultEntity authResult = null;
		logger.debug(String.format("获取到的回调参数: code=[%s],state=[%s]", code, state));
		
		//微信端访问
		
		MsDockConfig dockConfig =  commonDao.findUniqueByProperty(MsDockConfig.class,  "weixinAppid", state);
		
		if(dockConfig == null){
			throw new BusinessException("参数错误，无法登陆");
		}
		
		OAuthCallbackEntity oauthCallbackEntity = null;
		try {
			oauthCallbackEntity = wechatCoreService.getOpenidByOAuth(dockConfig, code, state);
		} catch (HttpRequestException e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException("网络异常，无法登录！");
		}
		
		logger.debug(String.format("OAuthCallbackEntity = %s", oauthCallbackEntity));
		
		if (oauthCallbackEntity != null && !StringUtils.isEmpty(oauthCallbackEntity.getOpenId())) {
			TrenchInfoEnity trenchInfo = new TrenchInfoEnity();
			trenchInfo.setSvcchnl("1");
			trenchInfo.setBindacct(oauthCallbackEntity.getOpenId());
			try {
				authResult = userServCoreService.tdPartAuthLogin(dockConfig.getWeixinAccountid(),trenchInfo);
			} catch (UserServBusinessException e) {
				logger.error(e.getMessage(), e);
				if ("20000002".equals(e.getErrorCode()))
					authResult = null;
				else
					throw e;
			} catch (HttpRequestException e) {
				logger.error(e.getMessage(), e);
				throw new BusinessException("网络异常，无法登录！");
			}
			//未找到注册信息
			if (authResult == null || StringUtils.isEmpty(authResult.getCn())) {
				session.setAttribute("user.openid", oauthCallbackEntity.getOpenId());
				session.setAttribute("user.weixinAccountId", dockConfig.getWeixinAccountid());
			}else{
				authResult.setWeixinAccountAppid(state);
				authResult.setWeixinAccountId(dockConfig.getWeixinAccountid());
			}
		}
		return authResult;
	}

}
