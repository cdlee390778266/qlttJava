package com.qianlong.qltt.us.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qltt.us.domain.comm.CommRsp;

import net.sf.json.JSONObject;

/**
 * 异常集中处理:
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	//private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception e) {
		CommRsp rsp = new CommRsp();
		if (e instanceof QlttUSException){
			rsp.setErrorCode(((QlttUSException) e).getExceptionCode());
			rsp.setErrorMsg(e.getMessage());
		}else if(e instanceof QlttReqValidException){
			rsp.setErrorCode(((QlttReqValidException)e).getExceptionCode());
			rsp.setErrorMsg(e.getMessage());
		}else{
			e.printStackTrace();
		}
		return handle(request, response, rsp, object);
	}

	/**
	 * 异常处理
	 */
	private ModelAndView handle(HttpServletRequest request, HttpServletResponse response, CommRsp rsp, Object object) {
		ModelAndView mv = new ModelAndView();
		/* 使用response返回 */
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		JSONObject jsonObject = JSONObject.fromObject(rsp);
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.write(jsonObject.toString());
			writer.flush();	
		} catch (IOException e) {
		}
		return mv;
	}
}
