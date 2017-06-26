package com.qianlong.qlttms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.JSONEntity;
import com.qianlong.qlttms.domain.QueryUserAcctTacRspBody;
import com.qianlong.qlttms.service.IIndexSystemService;
import com.qianlong.qlttms.service.IUserAcctTacMenuService;
import com.qianlong.qlttms.utils.Constants;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02001003;

/**
 * 指标查询
 * 
 * @author wangk
 */
@Controller
@RequestMapping("webapp/search")
public class IndexSearchController {

	private Logger logger = Logger.getLogger(IndexSearchController.class);

	@Autowired
	private IIndexSystemService indexSystemService;

	@Autowired
	private IUserAcctTacMenuService userAcctTacMenuService;

	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		logger.debug("进入搜索查询页面");
		return new ModelAndView("qianlong/search");
	}
	
	
	@RequestMapping("findgroups")
	@ResponseBody
	public Object findgroups(HttpServletRequest request) {
		
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		T02001002._rsp rsp = indexSystemService
				.queryIdxGroup(user.getWeixinAccountId());
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Object result = null;
		try {
			String json = JsonFormat.printer().print(rsp);
			logger.debug(
					String.format("T02001003 交易 - 转换后的JSON字符串: [%s]", json));
			result = JSONObject.parse(json);
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
		
	}
	

	@RequestMapping("child")
	@ResponseBody
	public Object child(HttpServletRequest request,
			@RequestParam(value = "tacgroup") String tacGroup) {
		logger.debug("获取指定指标组的指标");
		Object result = null;
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		if ("combination".equals(tacGroup)) { // 组合指标
			
			QueryUserAcctTacRspBody body = userAcctTacMenuService
					.query(user.getWeixinAccountId(), null, user.getTtacct());
			result = new JSONEntity(1, null, body);
			return result;
		} else {
			Map<String, Object> taggroup = new HashMap<String, Object>();
			taggroup.put("member", index(request, tacGroup));// 指标成员
			T02001002._rsp rsp = indexSystemService.queryIdxGroup(user.getWeixinAccountId());
			List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			if (!CollectionUtils.isEmpty(idxGroups)) {// 二级菜单
				Map<String, Object> map = null;
				for (T02001002._protacgroup protacgroup : idxGroups) {
					if (protacgroup.getPtacgroup().equals(tacGroup)) {
						map = new HashMap<String, Object>();
						try {
							map.put("info", JSONObject.parse(
									JsonFormat.printer().print(protacgroup)));
							map.put("member", index(request, protacgroup.getTacgroup()));
							children.add(map);
						} catch (InvalidProtocolBufferException e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
			taggroup.put("children", children);// 指标成员
			return taggroup;
			/*
			 * T02001003._rsp rsp =
			 * indexSystemService.queryIdxByGroup(tacGroup); try { String json =
			 * JsonFormat.printer().print(rsp); logger.debug(String.format(
			 * "T02001003 交易 - 转换后的JSON字符串: [%s]", json)); result =
			 * JSONObject.parse(json); } catch (InvalidProtocolBufferException
			 * e) { logger.error(e.getMessage(), e); }
			 */
		}

	}

	@RequestMapping("index")
	@ResponseBody
	public Object index(HttpServletRequest request, @RequestParam(value = "tacgroup") String tacGroup) {
		Object result = null;
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		T02001003._rsp rsp = indexSystemService.queryIdxByGroup(user.getWeixinAccountId(), tacGroup);
		try {
			String json = JsonFormat.printer().print(rsp);
			logger.debug(
					String.format("T02001003 交易 - 转换后的JSON字符串: [%s]", json));
			result = JSONObject.parse(json);
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
