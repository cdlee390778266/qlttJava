package com.qianlong.qltt.us.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSException;
import com.qianlong.qltt.us.service.IAccessTokenService;
import com.qianlong.qltt.us.util.StringUtil;
/**
 * @ClassName:     AccessTokeninterceptor.java 
 * @Description:   访问令牌拦截器：
 * 						1）检查令牌参数是否存在
 * 						2）令牌参数是否合法：令牌是否超时	
 * @author         sunchao/Email:sunchao@qianlong2.net
 * @version        V1.0   
 * @Date           2017年4月5日 下午1:46:24
 */
public class AccessTokenInterceptor extends BaseInterceptor{
	
	private  static final Logger logger = LoggerFactory.getLogger(AccessTokenInterceptor.class);
	
	@Autowired
	private IAccessTokenService accessTokenService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception{
		
		String uri = request.getRequestURI();
		logger.debug("当前请求URL："+ uri);
		logger.debug("当前拦截器：访问令牌拦截器");
		if(!StringUtil.contains(excludeMapping,uri)){
			//获取完整URI
			uri = uri.substring(request.getContextPath().length()+1,uri.indexOf("?") != -1?uri.indexOf("?"):uri.length());
			//验证Token参数是否存在
			String access_token = request.getParameter("access_token");
			if(!StringUtil.isNullOrBlank(access_token)){
				//检验access_token的合法性
				ServletContext context = request.getSession().getServletContext();
				accessTokenService.isAccessTokenLegal(context,access_token,uri);
			}else{//如果access_token不存在，抛出异常
				throw new QlttUSException(ErrorCodeMaster.TOKEN_NOT_EXIST);
			}
		}
		return super.preHandle(request, response, handler);
	}
}
