package com.qianlong.qltt.us.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.qianlong.qltt.us.exception.QlttReqValidException;
import com.qianlong.qltt.us.util.StringUtil;

public class BaseController {
	
	/**
	 * 获取HttpSession
	 */
	protected HttpSession getHttpSession(HttpServletRequest request){
		return request.getSession();
	}
	
	/**
	 * 获取ServletContext
	 */
	protected ServletContext getServletContext(HttpServletRequest request){
		return getHttpSession(request).getServletContext();
	}
	
	/**
	 * 处理请求数据格式验证的结果
	 */
	protected void handleReqParamerValid(BindingResult result){
		String errorMsg = getReqParamerErrormsg(result);
		if(!StringUtil.isNullOrBlank(errorMsg)){
			throw new QlttReqValidException(errorMsg, this);
		}
	}
	
	protected void handleReqParamerValid(List<BindingResult> results){
		if(results != null && !results.isEmpty()){
			StringBuilder msg = new StringBuilder();
			String rowMsg = null;
			for(BindingResult result:results){
				rowMsg = getReqParamerErrormsg(result);
				if(!StringUtil.isNullOrBlank(rowMsg)){
					msg.append(rowMsg);
				}
			}
			
			String msgStr = msg.toString();
			if(!StringUtil.isNullOrBlank(msgStr)){
				throw new QlttReqValidException(msgStr, this);
			}
		}
	}
	
	private String getReqParamerErrormsg(BindingResult result){
		if(result==null) return null;
		if(result.hasErrors()){ 
			StringBuilder msg = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError err : errors){
            	msg.append("{字段名称:");
            	msg.append(err.getField());
            	msg.append(",字段值:");
            	msg.append(err.getRejectedValue());
            	msg.append(",错误信息:");
            	msg.append(err.getDefaultMessage());
            	msg.append("}");
            }
            return msg.toString();
        }
		return null;
	}
}
