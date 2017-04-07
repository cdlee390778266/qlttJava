package com.qianlong.qltt.us.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSException;
import com.qianlong.qltt.us.service.IInterfaceCallInfoService;
import com.qianlong.qltt.us.util.StringUtil;
/**
 * @ClassName:     InterfaceCallInfoInterceptor.java 
 * @Description:   接口拦截器
 * 						1） 当前用户请求该接口是否已经已超出限制的次数
 * 						2）//TODO 关于接口权限可以在该拦截器中做
 * @author         sunchao/Email:sunchao@qianlong2.net
 * @version        V1.0   
 * @Date           2017年4月6日 上午9:40:24
 */
public class InterfaceCallInfoInterceptor extends BaseInterceptor{
	
	private  static final Logger logger = LoggerFactory.getLogger(InterfaceCallInfoInterceptor.class);
	
	@Autowired
	private IInterfaceCallInfoService interfaceCallInfoService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		String uri = request.getRequestURI();
		logger.debug("当前请求URL："+ uri);
		logger.debug("当前拦截器：接口拦截器"+ uri);
		if(!StringUtil.contains(excludeMapping,uri)){
			//获取完整URI
			uri = uri.substring(request.getContextPath().length(),uri.indexOf("?") != -1?uri.indexOf("?"):uri.length());
			//验证Token参数是否存在
			String access_token = request.getParameter("access_token");
			ServletContext context = request.getSession().getServletContext();
			if(!StringUtil.isNullOrBlank(access_token)){
				logger.debug("access_token:"+access_token);
				interfaceCallInfoService.callInterfaceValidByAccessToken(context,access_token,uri);
			}else{
				String appid =  request.getParameter("appid");
				if(!StringUtil.isNullOrBlank(appid)){
					logger.debug("appid:"+ appid);
					interfaceCallInfoService.callInterfaceValidByAppid(context,appid,uri);
				}else{
					logger.debug("参数中没有没有appid,也没有access_token");
					throw new QlttUSException(ErrorCodeMaster.CAN_NOT_TELL_REQUETER);
				}	
			}
				
		}
		return super.preHandle(request, response, handler);
	}
}
