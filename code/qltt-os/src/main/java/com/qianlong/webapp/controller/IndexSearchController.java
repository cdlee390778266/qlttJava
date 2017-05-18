package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.JSONEntity;
import com.qianlong.webapp.domain.QueryUserAcctTacRspBody;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qianlong.webapp.service.IUserAcctTacMenuService;
import com.qianlong.webapp.utils.Constants;
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
	public ModelAndView home() {
		logger.debug("进入搜索查询页面");
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Map<String, Object> model = new HashMap<>();
		model.put("idxGroups", idxGroups);
		return new ModelAndView("qianlong/search", model);
	}
	
	@RequestMapping("index")
	@ResponseBody
	public Object index(HttpServletRequest request, @RequestParam(value = "tacgroup")String tacGroup) {
		logger.debug("获取指定指标组的指标");
		Object result = null;
		
		if ("combination".equals(tacGroup)) {   //组合指标
			AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
			QueryUserAcctTacRspBody body = userAcctTacMenuService.query(null, user.getTtacct());
			result = new JSONEntity(1, null, body);
		} else {
			T02001003._rsp rsp = indexSystemService.queryIdxByGroup(tacGroup);
			try {
				String json = JsonFormat.printer().print(rsp);
				logger.debug(String.format("T02001003 交易 - 转换后的JSON字符串: [%s]", json));
				result = JSONObject.parse(json);
			} catch (InvalidProtocolBufferException e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return result;
	}
}
