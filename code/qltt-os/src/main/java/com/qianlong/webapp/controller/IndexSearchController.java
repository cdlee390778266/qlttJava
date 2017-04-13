package com.qianlong.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 指标查询
 * @author wangk
 *
 */
@Controller
@RequestMapping("webapp/search")
public class IndexSearchController {

	@RequestMapping("index")
	public ModelAndView index() {
		return new ModelAndView("qianlong/search");
	}
}
