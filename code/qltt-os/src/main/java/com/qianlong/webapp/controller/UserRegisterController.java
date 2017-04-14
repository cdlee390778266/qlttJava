package com.qianlong.webapp.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.OAuthCallbackEntity;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IWechatCoreService;
import com.qianlong.webapp.utils.AuthCodeGenerator;
import com.qianlong.webapp.utils.SMSSend;

import weixin.guanjia.account.entity.WeixinAccountEntity;

@Controller
@RequestMapping("webapp/register")
public class UserRegisterController {
	
	private Logger logger = Logger.getLogger(UserRegisterController.class);
	
	@Autowired
	private IWechatCoreService wechatCoreService;

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) {
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
		} else {
			//非微信端访问
		}
		
		return new ModelAndView("qianlong/register");
	}
	
	@RequestMapping("agreement")
	public ModelAndView agreement() {
		return new ModelAndView("qianlong/agreement");
	}
	
	@RequestMapping("validate")
	public ModelAndView validate(
			HttpServletRequest request,
			@RequestParam(required = true, value = "phone")String phone, 
			@RequestParam(required = true, value = "authCode")String authCode) {
		
		logger.debug(String.format("提交的手机号码为:[%s]，短信验证码为:[%s]", phone, authCode));
		HttpSession session = request.getSession();
		String recordPhone = (String)session.getAttribute("phone");
		String recordAuthCode = (String)session.getAttribute("authCode");
		long sendTime = (long)session.getAttribute("sendTime");
		long submitTime = Calendar.getInstance().getTimeInMillis();
		long duration = (submitTime - sendTime) / 1000;
		
		if (!phone.equals(recordPhone)) {
			throw new RuntimeException("手机号码不正确！");
		}
		if (!authCode.equals(recordAuthCode)) {
			throw new RuntimeException("验证码不匹配，请输入正确的验证码！");
		} else {
			if (duration > 180) {
				throw new RuntimeException("该验证码已失效，请重新获取！");
			}
		}
		return new ModelAndView("qianlong/validate");
	}
	
	@RequestMapping("obtainAuthCode")
	@ResponseBody
	public String obtainAuthCode(HttpServletRequest request, @RequestParam(required = true, value = "phone")String phone) {
		logger.debug(String.format("手机号码:[%s]", phone));
		String authCode = AuthCodeGenerator.produceAuthCode(6);
		HttpSession session = request.getSession();
		session.setAttribute("authCode", authCode);
		session.setAttribute("phone", phone);
		session.setAttribute("sendTime", Calendar.getInstance().getTimeInMillis());
		try {
			SMSSend.sendMessage("cdqianlong", "乾隆推推短信验证码", String.format("验证码是：%s", authCode), phone, 1);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return "发生错误，请重新获取验证码！";
		}
		return "请注意查收短信！";
	}
}
