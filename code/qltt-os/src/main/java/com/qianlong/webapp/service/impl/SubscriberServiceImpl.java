package com.qianlong.webapp.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.OAuthCallbackEntity;
import com.qianlong.webapp.domain.TrenchInfoEnity;
import com.qianlong.webapp.exception.BusinessException;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.exception.UserServBusinessException;
import com.qianlong.webapp.service.ISubscriberService;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.service.IWechatCoreService;

import weixin.guanjia.account.entity.WeixinAccountEntity;
import weixin.guanjia.account.service.WeixinAccountServiceI;

@Service
public class SubscriberServiceImpl implements ISubscriberService {
	
	private Logger logger = Logger.getLogger(SubscriberServiceImpl.class);
	
	@Autowired
	private IUserServCoreService userServCoreService;
	
	@Autowired
	private IWechatCoreService wechatCoreService;
	
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@Override
	public void save() {
		//
	}

	@Override
	public AuthResultEntity wechatUserLogin(String code, String state, HttpSession session) {
		AuthResultEntity authResult = null;
		logger.debug(String.format("获取到的回调参数: code=[%s],state=[%s]", code, state));
		
		//微信端访问
		WeixinAccountEntity account = new WeixinAccountEntity();
		List<WeixinAccountEntity> list = weixinAccountService.findByAccountAppID(state);
		if(CollectionUtils.isEmpty(list)){
			throw new BusinessException("参数错误，无法登陆");
		}else{
			account = list.get(0);
		}
		//account.setAccountappid(ResourceUtil.getConfigByName("wechat.accountAppId"));
		//account.setAccountappsecret(ResourceUtil.getConfigByName("wechat.accountAppSecret"));
		OAuthCallbackEntity oauthCallbackEntity = null;
		try {
			oauthCallbackEntity = wechatCoreService.getOpenidByOAuth(account, code, state);
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
				authResult = userServCoreService.tdPartAuthLogin(trenchInfo);
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
			}
		}
		
		return authResult;
	}

}
