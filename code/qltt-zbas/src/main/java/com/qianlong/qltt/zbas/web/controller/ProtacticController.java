package com.qianlong.qltt.zbas.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.Ttadefprotacprm;
import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.exception.ExceptionHandler;
import com.qianlong.qltt.zbas.service.IProtacticService;

/**
 * 原生指标Controller
 */
@Controller
@RequestMapping("/protactic")
public class ProtacticController {
	private static  final Logger logger = LoggerFactory.getLogger(ProtacticController.class);
	
	@Autowired 
	private IProtacticService protaticService;
	
	@RequestMapping("/golist.html")
	public String goToListPage() {
		return "protatic/list";
	}
	
	@RequestMapping("/list.html")
	@ResponseBody
	public Object list(Ttadefprotactic ttadefprotactic,Pagination pagination){
		return protaticService.selectProtacticPageData(ttadefprotactic,pagination);
	}
	
	@RequestMapping("/editProtactic.html")
	@ResponseBody
	public Object editProtactic(Ttadefprotactic ttadefprotactic,String zboper){
		logger.debug("/editProtactic.html");
		logger.debug("操作类型："+zboper);
		logger.debug("参数："+ttadefprotactic.toString());
		try {
			if("ADD".equals(zboper)){//如果是新增
				protaticService.insertProtactic(ttadefprotactic);
			}else if("EDIT".equals(zboper)){//如果是编辑
				protaticService.updateProtactic(ttadefprotactic);
			}else if("DELETE".equals(zboper)){// 如果是删除
				protaticService.deleteProtactic(ttadefprotactic);
			}else{
				return JsonResult.jsonError("未知操作");
			}
			return JsonResult.jsonOk();
		} catch (DuplicateKeyException e) {
			return JsonResult.jsonError("该指标已存在");
		}catch (Exception e) {
			return ExceptionHandler.handleException(e);
		}
	}
	
	@RequestMapping("/cslist.html")
	@ResponseBody
	public Object cslist(Ttadefprotacprm tadefprotacprm,Pagination pagination){
		return protaticService.selectProtacprmPageData(tadefprotacprm,pagination);
	}
	
	@RequestMapping("/editProtacprm.html")
	@ResponseBody
	public Object editProtacprm(Ttadefprotacprm ttadefprotacprm,String oper){
		logger.debug("/editProtacprm.html");
		logger.debug("操作类型："+oper);
		logger.debug("参数："+ttadefprotacprm.toString());
		try {
			if("ADD".equals(oper)){//如果是新增
				protaticService.insertProtacprm(ttadefprotacprm);
			}else if("EDIT".equals(oper)){//如果是编辑
				protaticService.updateProtacprm(ttadefprotacprm);
			}else if("DELETE".equals(oper)){// 如果是删除
				protaticService.deleteProtacprm(ttadefprotacprm);
			}else{
				return JsonResult.jsonError("未知操作");
			}
			return JsonResult.jsonOk();
		} catch (DuplicateKeyException e) {
			return JsonResult.jsonError("该指标参数已存在");
		}catch (Exception e) {
			return ExceptionHandler.handleException(e);
		}
	}
}
