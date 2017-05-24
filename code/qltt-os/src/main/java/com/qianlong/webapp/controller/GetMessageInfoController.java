package com.qianlong.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.service.IGetMessageInfoService;

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
		Map<String,Object> map = getDateMap(content);
	
		Map<String, Object> model = new HashMap<>();
		model.put("titleFirst", map.get("titleFirst"));
		model.put("titleLast", map.get("titleLast"));
		model.put("contentFirst", map.get("contentFirst"));
		model.put("contentLast", map.get("contentLast"));
		return new ModelAndView("qianlong/detail", model);
	}
	
	@RequestMapping("query1002")
	public ModelAndView query3001002(@RequestParam(value = "eventdate")String eventdate,@RequestParam(value = "eventno")Integer eventno,
			@RequestParam(value = "ttacct")String ttacct,@RequestParam(value = "svcchnl")Integer svcchnl) {
		logger.debug("进入即时推送数据详情页面，对应3001002");
		String content = iGetMessageInfoService.queryT3001002(eventdate, eventno, ttacct, svcchnl);
		Map<String,Object> map = getDateMap(content);
		
		Map<String, Object> model = new HashMap<>();
		model.put("titleFirst", map.get("titleFirst"));
		model.put("titleLast", map.get("titleLast"));
		model.put("contentFirst", map.get("contentFirst"));
		model.put("contentLast", map.get("contentLast"));
		return new ModelAndView("qianlong/detail", model);

	}
	
	private Map<String,Object> getDateMap(String content){
		Map<String,Object> getMap = new HashMap<>();
		List<Map<String,String>> contentFirst = new ArrayList<>();
		ArrayList<ArrayList<String>> contentLast= new ArrayList<ArrayList<String>>();
		String[] content1 = content.split("\n");
		//保利地产,600048|5.5%|2017-05-05 03:00:01
		for (int i = 1; i < content1.length; i++) {
			ArrayList<String> contentLastS = new ArrayList<>();
			Map<String,String> mapcontent1 = new HashMap<>();
			String[] con = content1[i].split("\\|");
			for (int j = 1; j < con.length; j++) {
				contentLastS.add(con[j]);
			}
			if(con[0].contains(",")){
				String[] contitle = con[0].split(",");
				mapcontent1.put("name", contitle[0].trim());
				mapcontent1.put("code", contitle[1].trim());
				contentFirst.add(mapcontent1);
			}else{
				mapcontent1.put("name", con[0].trim());
				mapcontent1.put("code", "");
				contentFirst.add(mapcontent1);
			}
			contentLast.add(contentLastS);		
		}
		String[] title = content1[0].split("\\|");
		List<Map<String,String>> titleLast = new ArrayList<>();
		for (int i = 1; i < title.length; i++) {
			Map<String,String> map = new HashMap<>();
			map.put("name", title[i]);
			titleLast.add(map);
		}
		String[] titleone = title[0].split(",");
		Map<String,String> titleFirst = new HashMap<>();
		titleFirst.put("name", titleone[0].trim());
		titleFirst.put("code", titleone[1].trim());
		getMap.put("titleFirst", titleFirst);
		getMap.put("titleLast", titleLast);
		getMap.put("contentFirst", contentFirst);
		getMap.put("contentLast", contentLast);
		return getMap;
	}
}
