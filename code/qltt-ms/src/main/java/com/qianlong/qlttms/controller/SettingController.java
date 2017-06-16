package com.qianlong.qlttms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.JSONEntity;
import com.qianlong.qlttms.domain.PushFreqEntity;
import com.qianlong.qlttms.domain.PushNumEntity;
import com.qianlong.qlttms.domain.PushScopeEntity;
import com.qianlong.qlttms.service.ISettingService;
import com.qianlong.qlttms.utils.Constants;

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
	
	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		Map<String, Object> model = new HashMap<>();
		PushFreqEntity pushFreq = settingService.getPushFreq(user.getWeixinAccountId(),user.getTtacct());
		PushScopeEntity pushScope = settingService.getPushScope(user.getWeixinAccountId(),user.getTtacct());
		PushNumEntity pushNum = settingService.getPushNum(user.getWeixinAccountId(),user.getTtacct());
		
		model.put("pushFreq", pushFreq.getPushFreq());
		model.put("pushScope", pushScope.getPushScope());
		model.put("scopePrm", pushScope.getScopePrm());
		model.put("pushNum", pushNum.getPushNum());
		return new ModelAndView("qianlong/setting", model);
	}
	
	@RequestMapping("freq")
	@ResponseBody
	public JSONEntity setPushFreq(HttpServletRequest request, @RequestParam(value = "pushFreq", required = true)Integer pushFreq) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		settingService.setPushFreq(user.getWeixinAccountId(),user.getTtacct(), pushFreq);
		return new JSONEntity(0, null, null);
	}
	
	@RequestMapping("scope")
	@ResponseBody
	public JSONEntity setPushScope(HttpServletRequest request, 
			@RequestParam(value = "pushScope", required = true)Integer pushScope,
			@RequestParam(value = "scopePrm", required = true)Integer scopePrm) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		settingService.setPushScope(user.getWeixinAccountId(),user.getTtacct(), pushScope, scopePrm);
		return new JSONEntity(0, null, null);
	}
}
