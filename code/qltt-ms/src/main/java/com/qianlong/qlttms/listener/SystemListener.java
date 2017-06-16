package com.qianlong.qlttms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qianlong.qlttms.service.IDockService;

public class SystemListener implements ServletContextListener,HttpSessionListener {

	private static ApplicationContext ctx = null;

	public SystemListener() {
	}

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		
	}

	/**
	 * 服务器初始化
	 */
	public void contextInitialized(ServletContextEvent event) {
		ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		IDockService dockService = (IDockService)webApplicationContext.getBean("dockService");

		dockService.loadDockConfig();
	
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}
	
	
	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
		
	}

}
