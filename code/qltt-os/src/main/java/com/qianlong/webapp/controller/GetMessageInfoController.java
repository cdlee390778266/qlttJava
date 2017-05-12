package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.qianlong.webapp.service.IGetMessageInfoService;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02001003;

/**
 * 即时推送数据详情页面
 * 
 * @author wenwen
 */
@Controller
@RequestMapping("webapp/getMessage")
public class GetMessageInfoController {
	
	private Logger logger = Logger.getLogger(GetMessageInfoController.class);
	
	@Autowired
	private IGetMessageInfoService iGetMessageInfoService;

	@RequestMapping("query1001")
	public ModelAndView query3001001(@RequestParam(value = "eventdate")String eventdate,@RequestParam(value = "eventno")Integer eventno,
			@RequestParam(value = "ttacct")String ttacct,@RequestParam(value = "svcchnl")Integer svcchnl) {
		logger.debug("进入即时推送数据详情页面，对应3001001");
		String content = iGetMessageInfoService.queryT3001001(eventdate, eventno, ttacct, svcchnl);
		Map<String, Object> model = new HashMap<>();
		model.put("content", content);
		return new ModelAndView("", model);
	}
	
	@RequestMapping("query1002")
	public ModelAndView query3001002(@RequestParam(value = "eventdate")String eventdate,@RequestParam(value = "eventno")Integer eventno,
			@RequestParam(value = "ttacct")String ttacct,@RequestParam(value = "svcchnl")Integer svcchnl) {
		logger.debug("进入即时推送数据详情页面，对应3001002");
		String content = iGetMessageInfoService.queryT3001002(eventdate, eventno, ttacct, svcchnl);
		Map<String, Object> model = new HashMap<>();
		model.put("content", content);
		return new ModelAndView("", model);

	}
}
