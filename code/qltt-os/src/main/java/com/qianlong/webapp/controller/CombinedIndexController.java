package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.CollectBody;
import com.qianlong.webapp.domain.JSONEntity;
import com.qianlong.webapp.service.ICollectService;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qianlong.webapp.utils.Constants;
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
	private ICollectService collectService;
	
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
	
	@RequestMapping("collect")
	@ResponseBody
	public JSONEntity collect(HttpServletRequest request, @RequestBody CollectBody collectBody) {
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		collectService.collect(collectBody.getIndices(), collectBody.getAddTacMenu(), user.getTtacct());
		return new JSONEntity(1, null, null);
	}
	
	@RequestMapping("follow")
	@ResponseBody
	public JSONEntity follow(HttpServletRequest request, @RequestBody CollectBody collectBody) {
		//
		return new JSONEntity(1, null, null);
	}
}
