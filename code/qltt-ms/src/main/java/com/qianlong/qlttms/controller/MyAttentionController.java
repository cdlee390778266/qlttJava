package com.qianlong.qlttms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.BaseIndex;
import com.qianlong.qlttms.domain.JSONEntity;
import com.qianlong.qlttms.service.IMyAttentionService;
import com.qianlong.qlttms.utils.Constants;

/**
 * 我的关注
 * 
 * @author wangk
 */
@Controller
@RequestMapping("webapp/myattention")
public class MyAttentionController {
	
	private Logger logger = Logger.getLogger(MyAttentionController.class);
	
	@Autowired
	private IMyAttentionService myAttentionService;

	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		logger.debug("进入我的关注页面");
		Map<String, Object> model = new HashMap<>();
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		List<BaseIndex> followingList = myAttentionService.followingList(user.getWeixinAccountId(),user.getTtacct());
		model.put("followingList", followingList);
		return new ModelAndView("qianlong/myattention", model);
	}
	
	
	@RequestMapping("followlist")
	@ResponseBody
	public Object followlist(HttpServletRequest request, BaseIndex index) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		List<BaseIndex> followingList = myAttentionService.followingList(user.getWeixinAccountId(), user.getTtacct());
		return followingList;
	}
	
	@RequestMapping("follow")
	@ResponseBody
	public JSONEntity follow(HttpServletRequest request, BaseIndex index) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		myAttentionService.follow(user.getWeixinAccountId(),index, user.getTtacct());
		return new JSONEntity(1, null, null);
	}
	
	@RequestMapping("isfollowed")
	@ResponseBody
	public JSONEntity isFollowed(HttpServletRequest request, BaseIndex index) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		return new JSONEntity(1, null, myAttentionService.isFollow(user.getWeixinAccountId(),index, user.getTtacct()));
	}
	
	
	@RequestMapping("unfollow")
	@ResponseBody
	public JSONEntity unfollow(HttpServletRequest request, BaseIndex index) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		myAttentionService.unfollow(user.getWeixinAccountId(),index, user.getTtacct());
		return new JSONEntity(1, null, null);
	}
}
