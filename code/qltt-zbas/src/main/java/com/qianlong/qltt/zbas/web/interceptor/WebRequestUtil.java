package com.qianlong.qltt.zbas.web.interceptor;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断是请求页面还是请求ajax
 * @author sunc 
 * @date : 2015-12-24 下午5:58:54
 */
public class WebRequestUtil {
	public static boolean isPageRequest(HttpServletRequest request){
		if(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))
			return false;
		return true;
	}
}
