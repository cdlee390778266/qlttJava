package com.qianlong.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
import com.qlcd.qltt.body.pvt.T02001003;
import com.qlcd.qltt.body.pvt.T02005002;
import com.sun.star.uno.RuntimeException;

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
/*		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Map<String, Object> model = new HashMap<>();
		model.put("idxGroups", idxGroups);*/
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		Map<String, Object> model = new HashMap<>();
		List<T02001002._protacgroup> _1level = new ArrayList<T02001002._protacgroup>();
		for (T02001002._protacgroup protacgroup :idxGroups) {
			if(protacgroup.getGrplevel() == 1){	
				_1level.add(protacgroup);
			}
		}
		model.put("idxGroups", _1level);
		return new ModelAndView("qianlong/combined", model);
	}
	
	private List<Object> getTacsByGroupid(String tacGroup) {
		T02001003._rsp rsp = indexSystemService.queryIdxByGroup(tacGroup);
		List<T02001003._protacgrpmem> protacgrpmems = rsp.getPtgmlistList();
		List<Object> list = new ArrayList<Object>();
		if(!CollectionUtils.isEmpty(protacgrpmems)){
			for(T02001003._protacgrpmem _protacgrpmem :protacgrpmems){
				String json;
				try {
					json = JsonFormat.printer().print(_protacgrpmem);
					list.add(JSONObject.parse(json)) ;
				} catch (InvalidProtocolBufferException e) {
					logger.error("转json失败",e);
					throw new RuntimeException("获取指标失败");
				}
			}	
		}
		return list;
	}
	
	@RequestMapping("grouptag")
	@ResponseBody
	public Object grouptag(@RequestParam(value = "tacgroup") String tacgroup) {
		logger.debug("获取指定一级指标组下面的所有指标");
		List<Object> protacgrpmems = new ArrayList<Object>();
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		List<T02001002._protacgroup> idxGroups = rsp.getPtglistList();
		getGroupTags(protacgrpmems, idxGroups, tacgroup);
		return protacgrpmems;
	}
	
	//获取一个指标组下面的所有指标
	private void getGroupTags(List<Object> protacgrpmem,List<T02001002._protacgroup> idxGroups,String tacGroup){
		if(!CollectionUtils.isEmpty(idxGroups)){
			 boolean isLeave = true;
			 for(T02001002._protacgroup _protacgroup:idxGroups ){
				 if(tacGroup.equals(_protacgroup.getPtacgroup())){
					 isLeave = false;
					getGroupTags(protacgrpmem,idxGroups, _protacgroup.getTacgroup());
				 }
			 }
			 if(isLeave){
				 protacgrpmem.addAll(getTacsByGroupid(tacGroup));
			 }
		}
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
