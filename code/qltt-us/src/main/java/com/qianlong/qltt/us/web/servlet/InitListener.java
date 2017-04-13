package com.qianlong.qltt.us.web.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qianlong.qltt.us.service.IInterfaceCallInfoService;

public class InitListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(InitListener.class);
	
	private IInterfaceCallInfoService interfaceCallInfoService;

	public void contextInitialized(ServletContextEvent context) {
		ServletContext sc = context.getServletContext();
		setInterfaceCallInfoService(sc);
		initConfig(sc);
	}
	
	public void contextDestroyed(ServletContextEvent context) {
		destroy(context.getServletContext());
	}
	
	
	private void setInterfaceCallInfoService(ServletContext sc) {
		IInterfaceCallInfoService interfaceCallInfoService = WebApplicationContextUtils.getWebApplicationContext(sc).getBean(IInterfaceCallInfoService.class);
		this.interfaceCallInfoService = interfaceCallInfoService;
	}

	/**
	 * 初始化系统配置
	 */
	private void initConfig(ServletContext sc) {
		logger.debug("InitListener启动");
		loadInterface(sc);
		loadConfig(sc);
		loadAcessTokenData(sc);
	}

	//往上下文环境中加载AccessToken相关数据
	private void loadAcessTokenData(ServletContext sc) {
		interfaceCallInfoService.loadAcessTokenData(sc);	
	}

	/**往上下文环境中加载config文件*/
	private void loadConfig(ServletContext sc) {
		String path = sc.getInitParameter("config");
		String configFileName = sc.getRealPath("/")+path;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(configFileName));
		} catch (IOException ex) {
			logger.error("加载系统参数失败");
			ex.printStackTrace();
		}
		Enumeration keys = properties.keys();
		while(keys.hasMoreElements()){
			String key = (String)keys.nextElement();
			sc.setAttribute(key, properties.getProperty(key));
		}
	}

	/**
	 * 加载所有接口信息
	 */
	private void loadInterface(ServletContext servletContext) {
		interfaceCallInfoService.loadAllInterfaceMap(servletContext);
	}
	

	//应用停的时候调用该方法
	private void destroy(ServletContext servletContext) {
		saveAcessTokenData(servletContext);	
	}

	private void saveAcessTokenData(ServletContext servletContext) {
		interfaceCallInfoService.saveAcessTokenData(servletContext);
	}
}