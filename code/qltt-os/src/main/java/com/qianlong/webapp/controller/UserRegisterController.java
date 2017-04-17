package com.qianlong.webapp.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.utils.AuthCodeGenerator;
import com.qianlong.webapp.utils.SMSSend;

@Controller
@RequestMapping("webapp/register")
public class UserRegisterController {
	
	private Logger logger = Logger.getLogger(UserRegisterController.class);

	@RequestMapping("index")
	public ModelAndView index() {
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
