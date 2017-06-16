package com.qianlong.qlttms.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.JSONEntity;
import com.qianlong.qlttms.domain.QueryFollowStockRspEntity;
import com.qianlong.qlttms.domain.UserServPageBean;
import com.qianlong.qlttms.domain.UserStockContent;
import com.qianlong.qlttms.service.IUserStockService;
import com.qianlong.qlttms.utils.Constants;

@Controller
@RequestMapping("webapp/userpool")
public class UserStockController {

	private Logger logger = Logger.getLogger(UserStockController.class);

	@Autowired
	private IUserStockService userStockService;

	@RequestMapping("home")
	public ModelAndView home() {
		logger.debug("进入选股池页面");
		return new ModelAndView("qianlong/userpool");
	}
	
	@RequestMapping("pool")
	@ResponseBody
	public JSONEntity userPool(HttpServletRequest request, UserServPageBean page, Integer poolIndex) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		QueryFollowStockRspEntity poolStock = userStockService.followingList(user.getWeixinAccountId(), page, poolIndex, user.getTtacct());
		JSONEntity entity = new JSONEntity(1, null, poolStock);
		return entity;
	}
	
	@RequestMapping("follow")
	@ResponseBody
	public JSONEntity follow(HttpServletRequest request, @RequestBody UserStockContent userStock) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		userStockService.follow(user.getWeixinAccountId(), userStock, user.getTtacct());
		JSONEntity entity = new JSONEntity(1, null, null);
		return entity;
	}
	
	@RequestMapping("unfollow")
	@ResponseBody
	public JSONEntity unfollow(HttpServletRequest request, @RequestBody UserStockContent userStock) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		userStockService.unfollow(user.getWeixinAccountId(), userStock, user.getTtacct());
		JSONEntity entity = new JSONEntity(1, null, null);
		return entity;
	}
}
