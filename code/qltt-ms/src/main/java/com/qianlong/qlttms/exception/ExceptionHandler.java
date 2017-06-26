package com.qianlong.qlttms.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qianlong.qlttms.domain.JSONEntity;
import com.qianlong.qlttms.utils.ExceptionUtil;

/**
 * spring mvc异常捕获类
 * 
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger.getLogger(ExceptionHandler.class);
	
	private PathMatcher matcher;
	
	private String mobilePath;

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		String simpleMessage = ex.getMessage();
		logger.error(String.format("全局异常统一处理 - 异常信息: %s", exceptionMessage));
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		model.put("ex", ex);
		model.put("simpleMessage", StringUtils.isEmpty(simpleMessage) ? "哎哟喂！页面让狗狗叼走了！" : simpleMessage);
		
		if(!(request.getHeader("accept").indexOf("application/json") > -1 
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			
			String contextPath = request.getContextPath();
			String validPath = String.format("%s/%s", contextPath, mobilePath);
			if (matcher.match(validPath, request.getRequestURI())) {
				return new ModelAndView("qianlong/error", model);
			} else {
				return new ModelAndView("common/error", model);
			}
		} else {
			try {
				JSONEntity rspEntity = new JSONEntity(0, ex.getMessage(), null);
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print(JSONObject.toJSONString(rspEntity));
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return null;
		}
	}

	public PathMatcher getMatcher() {
		return matcher;
	}

	public void setMatcher(PathMatcher matcher) {
		this.matcher = matcher;
	}

	public String getMobilePath() {
		return mobilePath;
	}

	public void setMobilePath(String mobilePath) {
		this.mobilePath = mobilePath;
	}
}
