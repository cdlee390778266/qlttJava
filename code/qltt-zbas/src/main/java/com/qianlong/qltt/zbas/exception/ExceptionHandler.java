package com.qianlong.qltt.zbas.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.TransactionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.util.JSONUtil;

/**
 * 异常集中处理:
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception e) {
		String message =null;
		if (e instanceof SessionException){
			message = "连接超时,请重新登陆...";
			return handle(request, response, "error/session", message);
		} else{
			logger.error("未知异常", e);
			message = e.getMessage();
			return handle(request, response, "error/error", message);
		}
	}

	/**
	 * 异常处理
	 */
	private ModelAndView handle(HttpServletRequest request,HttpServletResponse response,String view,String message){
		if(isJsonRequest(request)){
			write(response,JsonResult.jsonError(message));
			return null;
		}else{
			return showExceptionPage(view,message);
		}
	}
	
	/**
	 * 判断是否为json请求
	 */
	private boolean isJsonRequest(HttpServletRequest request) {
		boolean flag = request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1);
		return flag;
	}
	
	/**
	 * 将异常信息返回到页面上
	 */
	private ModelAndView showExceptionPage(String view, String message) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("error", message);
		return new ModelAndView(view, model);
	}

	/**
	 * 将信息写到response
	 */
	private void write(HttpServletResponse response,Map<String, Object> map){
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try{
			PrintWriter writer = response.getWriter();
			writer.write(JSONUtil.objToJson(map));
			writer.flush();
			writer.close();
		} catch (IOException e1) {
		}
	}
	
	public static Object handleException(Exception e){
		logger.error(getExceptionStackTrace(e));
		if(e instanceof DataAccessException){//统一处理DAO异常
			return handleDataAccessException((DataAccessException)e);
		}else if(e instanceof TransactionException){//事务异常
			return JsonResult.jsonError("数据库异常，请联系管理员");
		}else{
			return JsonResult.jsonError("系统异常，请联系管理员");
		}
	}

	private static Object handleDataAccessException(DataAccessException e){
		if(e instanceof DuplicateKeyException){
			return JsonResult.jsonError("该记录已存在，或违反唯一性约束");
		}else if(e instanceof DataIntegrityViolationException){
			return JsonResult.jsonError("该记录违反数据库约束");
		}
		return JsonResult.jsonError("操作失败,请稍后重试");
	}
	
	private static String  getExceptionStackTrace(Exception e){
		StringBuilder message = new StringBuilder();
		message.append(e.toString() + "\n");
		StackTraceElement[] element = e.getStackTrace();
		for (StackTraceElement row : element)
			message.append(row.toString() + "\n");
		return message.toString();
	}
}
