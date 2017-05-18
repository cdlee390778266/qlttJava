package com.qianlong.qltt.zbas.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.service.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static  final  Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping("/gopwd.html")
	public String goToPwdPage(){
		return "admin/pwd";
	}
	
	@RequestMapping("/chgpwd.html")
	@ResponseBody
	public Object chgPwd(Admin admin,String newpwd,HttpServletRequest request){
		return adminService.changePed(admin,newpwd,request.getSession().getServletContext());
	}
		
}
