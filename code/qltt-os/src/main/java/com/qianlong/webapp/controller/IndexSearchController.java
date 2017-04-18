package com.qianlong.webapp.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 指标查询
 * @author wangk
 */
@Controller
@RequestMapping("webapp/search")
public class IndexSearchController {
	
	private Logger logger = Logger.getLogger(IndexSearchController.class);

	@RequestMapping("index")
	public String index() {
		logger.debug("进入搜索查询页面");
		return "qianlong/search";
	}
}
