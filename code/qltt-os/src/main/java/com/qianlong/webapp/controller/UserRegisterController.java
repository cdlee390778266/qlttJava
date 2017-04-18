package com.qianlong.webapp.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.RegisterEntity;
import com.qianlong.webapp.domain.TrenchInfoEnity;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IUserServCoreService;
import com.qianlong.webapp.utils.AuthCodeGenerator;
import com.qianlong.webapp.utils.Constants;

@Controller
@RequestMapping("webapp/register")
public class UserRegisterController {
	
	private Logger logger = Logger.getLogger(UserRegisterController.class);
	
	@Autowired
	private IUserServCoreService userServCoreService;

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) {
		logger.debug("进入用户注册首页");
		return new ModelAndView("qianlong/register");
	}
	
	@RequestMapping("agreement")
	public ModelAndView agreement() {
		logger.debug("进入用户协议页面");
		return new ModelAndView("qianlong/agreement");
	}
	
	@RequestMapping("register")
	public ModelAndView register(
			HttpServletRequest request,
			@RequestParam(required = true, value = "phone")String phone, 
			@RequestParam(required = true, value = "code")String code) {
		
		logger.debug(String.format("提交的手机号码为:[%s]，短信验证码为:[%s]", phone, code));
		HttpSession session = request.getSession();
		String recordPhone = (String)session.getAttribute("phone");
		String recordCode = (String)session.getAttribute("code");
		long sendTime = (long)session.getAttribute("sendTime");
		long submitTime = Calendar.getInstance().getTimeInMillis();
		long duration = (submitTime - sendTime) / 1000;
		
		if (!phone.equals(recordPhone)) {
			throw new RuntimeException("手机号码不正确！");
		}
		if (!code.equals(recordCode)) {
			throw new RuntimeException("验证码不匹配，请输入正确的验证码！");
		} else {
			if (duration > 180) {
				throw new RuntimeException("该验证码已失效，请重新获取！");
			}
		}

		String openId = (String)session.getAttribute("user.openid");
		session.removeAttribute("user.openid");
		RegisterEntity regInfo = new RegisterEntity();
		regInfo.setCn(phone);
		TrenchInfoEnity acctBindInfo = new TrenchInfoEnity();
		acctBindInfo.setBindacct(openId);   //需要获取微信openid
		acctBindInfo.setSvcchnl("1");   //服务渠道，微信填'1'
		regInfo.setAcctBindInfo(acctBindInfo);
		
		AuthResultEntity result = null;
		try {
			result = userServCoreService.userAcctOpen(regInfo);
		} catch (HttpRequestException e) {
			logger.error(e.getMessage(), e);
			return new ModelAndView("qianlong/register");
		}
		
		session.setAttribute(Constants.LOGIN_USER_ACCOUNT, result);
		return new ModelAndView("qianlong/search");
	}
	
	@RequestMapping("code")
	@ResponseBody
	public String code(HttpServletRequest request, @RequestParam(required = true, value = "phone")String phone) {
		logger.debug(String.format("手机号码:[%s]", phone));
		String authCode = AuthCodeGenerator.produceAuthCode(6);
		HttpSession session = request.getSession();
		session.setAttribute("code", authCode);
		session.setAttribute("phone", phone);
		session.setAttribute("sendTime", Calendar.getInstance().getTimeInMillis());

		try {
			//SMSSend.sendMessage("cdqianlong", "乾隆推推短信验证码", String.format("验证码是：%s", authCode), phone, 1);
			logger.debug(authCode);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return "发生错误，请重新获取验证码！";
		}
		
		String message = String.format("请注意查收短信！[%s]", authCode);
		return message;
	}
}
