package com.qianlong.qltt.zbas.web.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.entity.ConnectionParam;
import com.qianlong.qltt.zbas.entity.TableInfo;
import com.qianlong.qltt.zbas.service.ITableManageService;
/**
 * TableManageController:数据库表复制
 */
@Controller
@RequestMapping("/tablemanage")
public class TableManageController {
	
	@Autowired 
	private ITableManageService tableManageService;
	
	/**
	 * 进行库表复制界面
	 */
	@RequestMapping("/golist.html")
	public String goToListPage(Model model){
		return "tablemanage/list";
	}
	
	/**
	 * 查询数据库表
	 */
	@RequestMapping("/srcdbtables.html")
	@ResponseBody
	public Object getSourceDBTables(){
		ConnectionParam param = tableManageService.getSourceDBConnectionParam();
		List<TableInfo> infos = tableManageService.getTables(param);
		return infos;
	}
	
	/**
	 * 查询数据库表
	 */
	@RequestMapping("/tgtdbtables.html")
	@ResponseBody
	public Object getTargetDBTables(@Param(value="host")String host,
			@Param(value="port") String port,
			@Param(value="database") String database,
			@Param(value="username") String username,
			@Param(value="password") String password){
		ConnectionParam  param = tableManageService.getDBConnectionParam(host,port,database,username,password);
		List<TableInfo> infos = tableManageService.getTables(param);
		return infos;
	}
	
	/**
	 * 进行数据库表拷贝
	 */
	@RequestMapping("/copytables.html")
	@ResponseBody
	public Object copyTables(@Param(value="host")String host,
			@Param(value="port") String port,
			@Param(value="database") String database,
			@Param(value="username") String username,
			@Param(value="password") String password,@RequestParam(value="tablenames[]",required = true) String[] tablenames){
		ConnectionParam sourceConnectionParam = tableManageService.getSourceDBConnectionParam();
		ConnectionParam  targetConnectionParam = tableManageService.getDBConnectionParam(host,port,database,username,password);
		tableManageService.copyTables(sourceConnectionParam,targetConnectionParam,tablenames);
		return JsonResult.jsonOk();
	}
}
