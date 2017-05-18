package com.qianlong.qltt.zbas.service;

import java.util.Map;

import javax.servlet.ServletContext;

import com.qianlong.qltt.zbas.entity.Admin;

public interface IAdminService {
	/**验证用户*/
	Map<String, Object> validAdmin(Admin admin,ServletContext servlet);

	Map<String, Object> changePed(Admin admin, String newpwd,ServletContext servletContext);

}
