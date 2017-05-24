package com.qianlong.qltt.zbas.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.zbas.common.Constants;
import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.entity.Menu;
import com.qianlong.qltt.zbas.service.IAdminService;
import com.qianlong.qltt.zbas.service.IMenuService;

/**
 * @ClassName: LoginController.java
 * @Description: 登陆Controller
 * @author sunchao/Email:sunchao@qianlong2.net
 * @version V1.0
 * @Date 2017年3月1日 下午3:30:22
 */
@Controller
public class LoginController {
	private static  final  Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IMenuService menuService;
	  
	@RequestMapping("/index.html")
	public String index() {
		logger.debug("/index.html");
		return "index";
	}
	
	@RequestMapping("/login.html")
	public String login(Admin admin,Model model,HttpServletRequest request){
		logger.debug("/login.html,参数："+admin.toString());
		Map<String, Object> map = adminService.validAdmin(admin,request.getSession().getServletContext());
		if("ok".equals(map.get("result"))){
			request.getSession().setAttribute(Constants.ADMINISTRATOR, admin);
			return "redirect:/main.html";
		}else{
			model.addAttribute("admin", admin);
			model.addAttribute("errorMsg",map.get("message"));
			return "index";
		}
	}
	
	@RequestMapping("/main.html")
	public String main(){
		logger.debug("/main.html");
		return "main";
	}
	
	@RequestMapping("/menutree.html")
	@ResponseBody
	public Object getUserMenuTreeData(HttpServletRequest request){
		logger.debug("/menutree.html");
		Admin admin = (Admin)(request.getSession().getAttribute(Constants.ADMINISTRATOR));
		List<Menu> menus = menuService.selectMenu(admin);
		List<Map<String, Object>>  list = menuService.convertMenusToMap(menus,null);
		return list;
	}
	
	@RequestMapping("/logout.html")
	public String logout(HttpServletRequest request){
		logger.debug("/logout.html");
		request.getSession().removeAttribute(Constants.ADMINISTRATOR);
		return "index";
	}
}
