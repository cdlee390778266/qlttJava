package com.qianlong.qltt.zbas.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.TtadefcombtacprmKey;
import com.qianlong.qltt.zbas.domain.Ttadefcombtactic;
import com.qianlong.qltt.zbas.service.ICombtacticService;

/**
 * 组合指标Controller
 */
@Controller
@RequestMapping("/combtactic")
public class CombtacticController {
	private static  final Logger logger = LoggerFactory.getLogger(CombtacticController.class);
	
	@Autowired 
	private ICombtacticService combtacticService;
	
	@RequestMapping("/golist.html")
	public String goToListPage() {
		return "combtactic/list";
	}
	
	@RequestMapping("/list.html")
	@ResponseBody
	public Object list(Ttadefcombtactic ttadefcombtactic,Pagination pagination){
		return JsonResult.jsonData(combtacticService.selectProtacticPageData(ttadefcombtactic,pagination));
	}
	
	@RequestMapping("/cslist.html")
	@ResponseBody
	public Object cslist(TtadefcombtacprmKey ttadefcombtacprmKey,Pagination pagination){
		return JsonResult.jsonData(combtacticService.selectProtacticPageData(ttadefcombtacprmKey,pagination));
	}
}
