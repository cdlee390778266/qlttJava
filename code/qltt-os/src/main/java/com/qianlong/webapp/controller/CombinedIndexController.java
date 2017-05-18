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

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.domain.CollectBody;
import com.qianlong.webapp.domain.FilterBody;
import com.qianlong.webapp.domain.JSONEntity;
import com.qianlong.webapp.service.ICollectService;
import com.qianlong.webapp.service.ICombinedIndexService;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qianlong.webapp.utils.Constants;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02005002;

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
	
	@Autowired
	private ICombinedIndexService combinedIndexService;

	@RequestMapping("home")
	public ModelAndView home(HttpServletRequest request) {
		logger.debug("进入组合指标页面");
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Map<String, Object> model = new HashMap<>();
		model.put("idxGroups", idxGroups);
		return new ModelAndView("qianlong/combined", model);
	}
	
	/**
	 * 收藏组合指标
	 * @param request
	 * @param collectBody
	 * @return
	 */
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
		return new JSONEntity(1, null, null);
	}
	
	/**
	 * 根据指标组合过滤股票
	 * @param request
	 * @param indices
	 * @return
	 */
	@RequestMapping("filtration")
	@ResponseBody
	public Object filtration(HttpServletRequest request, @RequestBody FilterBody filter) {
		T02005002._rsp rsp = combinedIndexService.filtration(filter);
		Object result = null;
		try {
			String json = JsonFormat.printer().print(rsp);
			result = JSONObject.parse(json);
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
