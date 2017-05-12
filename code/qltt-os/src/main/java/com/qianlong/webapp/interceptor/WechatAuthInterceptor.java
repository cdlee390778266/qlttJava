package com.qianlong.webapp.interceptor;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.webapp.domain.AuthResultEntity;
import com.qianlong.webapp.utils.Constants;

/**
 * 微网站访问权限过滤
 * @author Wangk
 */
public class WechatAuthInterceptor implements HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(WechatAuthInterceptor.class);
	
	private List<String> excludeUrls;

	/**
	 * 在controller前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath  = ResourceUtil.getJustRequestPath(request);   //用户访问的资源地址
		logger.debug(String.format("本次请求的URL:%s", requestPath));
		
		Enumeration<?> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String)headerNames.nextElement();
			Enumeration<?> headers = request.getHeaders(headerName);
			int idx = 0;
			while (headers.hasMoreElements()) {
				logger.debug(String.format("Header - header[%s][%d]={%s}", headerName, idx, headers.nextElement()));
				idx ++;
			}
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				logger.debug(String.format("Cookie - Name: [%s], Value: [%s], Path: [%s], MaxAge: [%d], Domain: [%s], Comment: [%s], Secure: [%s], Version: [%s]", 
						cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getMaxAge(), cookie.getDomain(), cookie.getComment(), cookie.getSecure(), cookie.getVersion()));
			}
		}
		
		logger.debug(String.format("Session - sessionid: [%s]", request.getSession().getId()));
		logger.debug(String.format("Session - hash code: [%s]", request.getSession()));
		
		if (excludeUrls.contains(requestPath)) {
			return true;
		}
		
		AuthResultEntity user = (AuthResultEntity)request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		if (user == null || StringUtils.isEmpty(user.getTtacct())) {
			response.sendRedirect("../register/home.do");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("WechatAuthInterceptor.postHandle方法通过");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("WechatAuthInterceptor.afterCompletion方法通过");
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}
