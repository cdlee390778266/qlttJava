package com.qianlong.qltt.us.web.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class BaseInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 不需要拦截的URI Mapping
	 */
	protected String[] excludeMapping;
	
	public void setExcludeMapping(String excludeMapping){
		String[] temp = excludeMapping.split(",");
		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].replaceAll("\\*", "");
		this.excludeMapping = temp;
	}
}
