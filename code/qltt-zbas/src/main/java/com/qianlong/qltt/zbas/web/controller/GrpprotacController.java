package com.qianlong.qltt.zbas.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.Ttagrpprotac;
import com.qianlong.qltt.zbas.exception.ExceptionHandler;
import com.qianlong.qltt.zbas.service.IGrpprotacService;

/**
 * 指标分组管理Controller
 */
@Controller
@RequestMapping("/grpprotac")
public class GrpprotacController {
	private static  final Logger logger = LoggerFactory.getLogger(GrpprotacController.class);
	
	@Autowired 
	private IGrpprotacService grpprotacService;
	
	@RequestMapping("/golist.html")
	public String goToListPage() {
		return "grpprotac/list";
	}
	
	@RequestMapping("/treelist.html")
	@ResponseBody
	public Object getTreeList(){
	    List<Ttagrpprotac> ttagrpprotacs = grpprotacService.selectGrpprotacTree(1,null,null);
		return ttagrpprotacs;
	}
	
	@RequestMapping("/selectUnselectZB.html")
	@ResponseBody
	public Object selectUnselectZB(@RequestParam(value="grpcode" ,required=false)String grpcode){
		logger.debug("/selectUnselectZB.html");
		try {
			List<Ttadefprotactic> list =  grpprotacService.selectUnselectZB(grpcode);
			return JsonResult.jsonData(list);
		}catch (Exception e) {
			return ExceptionHandler.handleException(e);
		}
	}
	
	@RequestMapping("/checkGrpCode.html")
	@ResponseBody
	public Object checkGrpCode(Ttagrpprotac ttagrpprotac){
		try{
			return  grpprotacService.checkGrpCode(ttagrpprotac);
		}catch (Exception e) {
			return ExceptionHandler.handleException(e);
		}
	}
	
	@RequestMapping("/saveTacgroup.html")
	@ResponseBody
	public Object saveTacgroup(Ttagrpprotac ttagrpprotac,
			@RequestParam(value="oper",required=true)String oper){
		try{
			return  grpprotacService.saveTacgroup(ttagrpprotac,ttagrpprotac.getMember(),oper);
		}catch (Exception e) {
			return ExceptionHandler.handleException(e);
		}
	}
	
}
