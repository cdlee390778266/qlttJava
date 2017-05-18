package com.qianlong.qltt.zbas.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.service.IAdminService;
import com.qianlong.qltt.zbas.util.PropertiesUtil;
import com.qianlong.qltt.zbas.util.StringUtil;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);
	public static  final String  ADMIN_FILE = "/WEB-INF/user.properties";
	
	@Override
	public Map<String, Object> validAdmin(Admin admin, ServletContext servletContext) {
		String filename = servletContext.getRealPath(ADMIN_FILE);
		logger.debug("properties path:"+ filename);
		try {
			Properties adminprop = PropertiesUtil.loadProperties(filename);
			String username = adminprop.getProperty("username");// 从properties读取用户名或密码
			String password =  adminprop.getProperty("password");
			if(StringUtil.nullOrBlank(username) || password == null){
				return JsonResult.jsonError("系统错误，请联系管理员");
			}
			if(username.equals(admin.getUsername().trim()) 
					&& password.equals(admin.getPassword())){//验证用户名
				return JsonResult.jsonOk();
			}else{
				return JsonResult.jsonError("用户名或密码错误");
			}
		} catch (IOException e) {
			return JsonResult.jsonError("系统错误，请联系管理员");
		}
	}

	@Override
	public Map<String, Object> changePed(Admin admin, String newpwd,ServletContext servletContext) {
		String filename = servletContext.getRealPath(ADMIN_FILE);
		logger.debug("properties path:"+ filename);
		try {
			Properties adminprop = PropertiesUtil.loadProperties(filename);
			String username = adminprop.getProperty("username");// 从properties读取用户名或密码
			String password =  adminprop.getProperty("password");
			if(StringUtil.nullOrBlank(username) || password == null){
				return JsonResult.jsonError("系统错误，请联系管理员");
			}
			if(username.equals(admin.getUsername().trim()) 
					&& password.equals(admin.getPassword())){//验证用户名
				PropertiesUtil.updateProperties(filename, "password", newpwd);
				return JsonResult.jsonOk();
			}else{
				return JsonResult.jsonError("用户名或密码错误");
			}
		} catch (IOException e) {
			return JsonResult.jsonError("系统错误，请联系管理员");
		}
	}	
}
