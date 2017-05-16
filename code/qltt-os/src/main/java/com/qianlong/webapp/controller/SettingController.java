package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.PushFreqEntity;
import com.qianlong.webapp.domain.PushNumEntity;
import com.qianlong.webapp.domain.PushScopeEntity;
import com.qianlong.webapp.exception.BusinessException;
import com.qianlong.webapp.service.ISettingService;
import com.qianlong.webapp.service.ISubscriberService;
import com.qianlong.webapp.utils.Constants;

/**
 * 全局推送设置
 * @author wangk
 */
@Controller
@RequestMapping("webapp/setting")
public class SettingController {
	
	private Logger logger = Logger.getLogger(SettingController.class);

	@Autowired
	private ISettingService settingService;
	
	@Autowired
	private ISubscriberService subscriberService;
	
	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		Map<String, Object> model = new HashMap<>();
		
		if (user == null) {
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			logger.debug(String.format("获取到的回调参数: code=[%s],state=[%s]", code, state));
			HttpSession session = request.getSession();
			
			if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(state)) {
				user = subscriberService.wechatUserLogin(code, state, session);
				//未找到注册信息，跳转到注册界面
				if (user == null || StringUtils.isEmpty(user.getCn())) {
					return new ModelAndView("redirect:/webapp/register/home.do");
				} else {
					session.setAttribute(Constants.LOGIN_USER_ACCOUNT, user);
				}
			} else {
				//非微信端访问
				throw new BusinessException("暂不支持其它客户端访问！");
			}
		}
		
		PushFreqEntity pushFreq = settingService.getPushFreq(user.getTtacct());
		PushScopeEntity pushScope = settingService.getPushScope(user.getTtacct());
		PushNumEntity pushNum = settingService.getPushNum(user.getTtacct());
		
		model.put("pushFreq", pushFreq);
		model.put("pushScope", pushScope);
		model.put("pushNum", pushNum);
		return new ModelAndView("qianlong/setting", model);
	}
}
