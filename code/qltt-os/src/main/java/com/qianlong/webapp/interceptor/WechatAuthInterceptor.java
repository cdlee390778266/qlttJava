package com.qianlong.webapp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

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
		if (excludeUrls.contains(requestPath)) {
			return true;
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

}