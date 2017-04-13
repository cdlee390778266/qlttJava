package com.qianlong.qltt.us.service;


import javax.servlet.ServletContext;

public interface IInterfaceCallInfoService extends ICommService{
	
	/**
	 * 接口限制验证的调用：上传了access_token的参数
	 * 		目前只验证了接口调用次数
	 */
	void callInterfaceValidByAccessToken(ServletContext context, String access_token, String uri);
	
	/**
	 * 接口限制验证的调用：没有上传access_token
	 * 		目前只验证了接口调用次数
	 */
	void callInterfaceValidByAppid(ServletContext context, String appid, String uri);

	/**
	 * 加载所有接口信息
	 */
	void loadAllInterfaceMap(ServletContext servletContext);

	/**
	 * 将临时表中的数据加载到内存中，数据加载完后，清空临时表中数据
	 */
	void loadAcessTokenData(ServletContext sc);

	/**
	 * 把accessToken相关数据存放到临时表中
	 */
	void saveAcessTokenData(ServletContext servletContext);
}
