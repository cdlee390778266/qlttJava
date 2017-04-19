package org.jeecgframework.core.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ExceptionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc异常捕获类
 * 
 */
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		logger.error(String.format("全局异常统一处理 - 异常信息: %s", exceptionMessage));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		model.put("ex", ex);
		
		if(!(request.getHeader("accept").indexOf("application/json") > -1 
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			
			return new ModelAndView("common/error", model);
		} else {
			try {
				PrintWriter out = response.getWriter();
				out.print(ex.getMessage());
				out.flush();
				out.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return null;
		}
	}
}
