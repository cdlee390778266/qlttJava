package com.qianlong.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.OAuthCallbackEntity;
import com.qianlong.webapp.domain.TrenchInfoEnity;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.service.IWechatCoreService;
import com.qianlong.webapp.utils.Constants;

import weixin.guanjia.account.entity.WeixinAccountEntity;

/**
 * 指标查询
 * @author wangk
 *
 */
@Controller
@RequestMapping("webapp/search")
public class IndexSearchController {
	
	private Logger logger = Logger.getLogger(IndexSearchController.class);
	
	@Autowired
	private IWechatCoreService wechatCoreService;
	
	@Autowired
	private IUserServCoreService userServCoreService;

	@RequestMapping("index")
	public String index(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		logger.debug(String.format("获取到的回调参数:code=[%s],state=[%s]", code, state));

		if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(state)) {
			//微信端访问
			WeixinAccountEntity account = new WeixinAccountEntity();
			account.setAccountappid("wx5d31a5be817a1b47");
			account.setAccountappsecret("32be6a017915bd351104e214e28e184c");
			OAuthCallbackEntity oauthCallbackEntity = null;
			try {
				oauthCallbackEntity = wechatCoreService.getOpenidByOAuth(account, code, state);
			} catch (HttpRequestException e) {
				logger.error(e.getMessage(), e);
			}
			
			logger.debug(String.format("OAuthCallbackEntity = %s", oauthCallbackEntity));
			
			if (oauthCallbackEntity != null && !StringUtils.isEmpty(oauthCallbackEntity.getOpenId())) {
				TrenchInfoEnity trenchInfo = new TrenchInfoEnity();
				trenchInfo.setSvcchnl("1");
				trenchInfo.setBindacct(oauthCallbackEntity.getOpenId());
				AuthResultEntity authResult = null;
				try {
					authResult = userServCoreService.tdPartAuthLogin(trenchInfo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				//未找到注册信息，跳转到注册界面
				if (authResult == null || StringUtils.isEmpty(authResult.getCn())) {
					return "redirect:/webapp/register/index.do";
				} else {
					request.getSession().setAttribute(Constants.LOGIN_USER_ACCOUNT, authResult);
				}
			}
		} else {
			//非微信端访问
			return "redirect:/webapp/register/index.do";
		}
		return "qianlong/search";
	}
}
