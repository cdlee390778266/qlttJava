package com.qianlong.qltt.zbas.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qianlong.qltt.zbas.common.Constants;
import com.qianlong.qltt.zbas.entity.Admin;
import com.qianlong.qltt.zbas.exception.SessionException;

public class SessionInterceptor extends HandlerInterceptorAdapter {
	
	protected static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	//放行的URL
	private String[] excludeMapping;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler) throws Exception {
		
		HttpSession session = request.getSession();//获取session
		
		String currentURI = request.getRequestURI().substring(request.getContextPath().length());//当前的URi
		logger.debug("当前URI:"+currentURI);
		
		boolean checkFlg = false;
		if (excludeMapping != null){
			for(String excludeURI : excludeMapping){
				if(currentURI.contains(excludeURI)){
					checkFlg = true;
					break;
				}
			}
		}
		if(!checkFlg){//不是放行路径
			Admin admin = (Admin)session.getAttribute(Constants.ADMINISTRATOR);
			if(admin == null){
				throw new SessionException();
			}
		}
		return super.preHandle(request, response, handler);
	}

	public void setExcludeMapping(String excludeMapping) {
		String[] temp = excludeMapping.split(",");
		for (int i = 0; i < temp.length; i++)
			temp[i] = temp[i].replaceAll("\\*", "");
		this.excludeMapping = temp;
	}
}