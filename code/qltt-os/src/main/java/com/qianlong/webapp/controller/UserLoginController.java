package com.qianlong.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.exception.BusinessException;
import com.qianlong.webapp.service.ISubscriberService;
import com.qianlong.webapp.utils.Constants;

@Controller
@RequestMapping("webapp/logon")
public class UserLoginController {

	private Logger logger = Logger.getLogger(UserLoginController.class);
	
	@Autowired
	private ISubscriberService subscriberService;
	
	@RequestMapping("home")
	public ModelAndView home() {
		logger.debug("进入用户登录首页");
		return new ModelAndView("qianlong/logon");
	}
	
	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		logger.debug(String.format("获取到的回调参数: code=[%s],state=[%s]", code, state));
		HttpSession session = request.getSession();

		if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(state)) {
			AuthResultEntity authResult = subscriberService.wechatUserLogin(code, state, session);
			//未找到注册信息，跳转到注册界面
			if (authResult == null || StringUtils.isEmpty(authResult.getCn())) {
				return new ModelAndView("redirect:/webapp/register/home.do");
			} else {
				session.setAttribute(Constants.LOGIN_USER_ACCOUNT, authResult);
				return new ModelAndView("redirect:/webapp/search/home.do");
			}
		} else {
			//非微信端访问
			throw new BusinessException("暂不支持其它客户端访问！");
		}
	}
}
