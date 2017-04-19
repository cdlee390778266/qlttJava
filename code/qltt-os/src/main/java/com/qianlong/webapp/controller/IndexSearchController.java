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

import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02001003;

/**
 * 指标查询
 * @author wangk
 */
@Controller
@RequestMapping("webapp/search")
public class IndexSearchController {
	
	private Logger logger = Logger.getLogger(IndexSearchController.class);
	
	@Autowired
	private IIndexSystemService indexSystemService;

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
	public List<T02001003._protacgrpmem> index(@RequestParam(value = "tacgroup")String tacGroup) {
		logger.debug("获取指定指标组的指标");
		T02001003._rsp rsp = indexSystemService.queryIdxByGroup(tacGroup);
		List<T02001003._protacgrpmem> groupMembers = rsp.getPtgmlistList();
		return groupMembers;
	}
}
