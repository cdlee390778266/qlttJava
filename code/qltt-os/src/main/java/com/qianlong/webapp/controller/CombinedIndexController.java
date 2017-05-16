package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.service.ICombinedIndexService;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.pvt.T02001002;

/**
 * 组合指标
 * @author wangk
 *
 */
@Controller
@RequestMapping("webapp/combined")
public class CombinedIndexController {

	private Logger logger = Logger.getLogger(CombinedIndexController.class);
	
	@Autowired
	private ICombinedIndexService combinedIndexService;
	
	@Autowired
	private IIndexSystemService indexSystemService;

	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		logger.debug("进入组合指标页面");
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Map<String, Object> model = new HashMap<>();
		model.put("idxGroups", idxGroups);
		return new ModelAndView("qianlong/combined", model);
	}
}
