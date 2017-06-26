package com.qianlong.qlttms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qlttms.service.ICommonDBService;
import com.qianlong.qlttms.domain.db.NameTable;

@Scope("prototype")
@Controller
@RequestMapping("webapp/nametable/")
public class NameTableController {
	
	
	private Logger logger = Logger.getLogger(NameTableController.class);
	
	@Autowired
	private ICommonDBService commonDBService;
	
	
	@RequestMapping("search")
	@ResponseBody
	public Object search(HttpServletRequest request,
			@RequestParam(value = "key") String key) {
		
		logger.debug("search key:"+key);
		String hql = "FROM NameTable where fsKindID != '0' and fsCode like '%"+key+"%'";
		List<NameTable> nameTables = commonDBService.findMaxResultHql(hql, 20);
		logger.debug("NameTable size"+ nameTables.size());
		return nameTables;
	}
	
	
	

}
