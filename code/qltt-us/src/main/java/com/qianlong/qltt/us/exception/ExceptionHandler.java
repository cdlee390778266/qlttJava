package com.qianlong.qltt.us.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.NonTransientDataAccessResourceException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.CannotReadScriptException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.transaction.TransactionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qianlong.qltt.us.domain.comm.CommRsp;

import net.sf.json.JSONObject;

/**
 * 异常集中处理:
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception e) {
		CommRsp rsp = new CommRsp();
		
		if (e instanceof QlttUSException){
			logger.info(getExceptionStackTrace(e));
			rsp.setErrorCode(((QlttUSException) e).getExceptionCode());
		}else{
			logger.error(getExceptionStackTrace(e));
			if(e instanceof DataAccessException){//统一处理DAO异常
				handleDataAccessException((DataAccessException)e,rsp);
			}else if(e instanceof TransactionException){//事务异常
				rsp.setErrorCode(ErrorCodeMaster.TransactionException);	
			}else{
				rsp.setErrorCode(ErrorCodeMaster.RE_RUNTIMEEXCEPTION);
			}
		}
		rsp.setErrorMsg(ErrorCodeMaster.getMessage(rsp.getErrorCode()));
		return handle(request, response, rsp, object);
	}

	private void handleDataAccessException(DataAccessException e, CommRsp rsp) {
		if (e instanceof CleanupFailureDataAccessException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_CleanupFailureDataAccessException);
		}else if(e instanceof DataIntegrityViolationException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_DataIntegrityViolationException);
		}else if(e instanceof DataRetrievalFailureException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_DataRetrievalFailureException);
		}else if(e instanceof DataSourceLookupFailureException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_DataSourceLookupFailureException);
		}else if(e instanceof InvalidDataAccessApiUsageException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_InvalidDataAccessApiUsageException);
		}else if(e instanceof InvalidDataAccessResourceUsageException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_InvalidDataAccessResourceUsageException);
		}else if(e instanceof NonTransientDataAccessResourceException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_NonTransientDataAccessResourceException);
		}else if(e instanceof PermissionDeniedDataAccessException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_PermissionDeniedDataAccessException);
		}else if(e instanceof UncategorizedDataAccessException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_UncategorizedDataAccessException);
		}else if(e instanceof RecoverableDataAccessException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_RecoverableDataAccessException);
		}else if(e instanceof CannotReadScriptException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_CannotReadScriptException);
		}else if(e instanceof OptimisticLockingFailureException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_OptimisticLockingFailureException);
		}else if(e instanceof PessimisticLockingFailureException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_PessimisticLockingFailureException);
		}else if(e instanceof QueryTimeoutException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_QueryTimeoutException);
		}else if(e instanceof TransientDataAccessResourceException){
			rsp.setErrorCode(ErrorCodeMaster.DAO_TransientDataAccessResourceException);
		}
	}

	private String getExceptionStackTrace(Exception e){
		StringBuilder message = new StringBuilder();
		message.append(e.toString() + "\n");
		StackTraceElement[] element = e.getStackTrace();
		for (StackTraceElement row : element)
			message.append(row.toString() + "\n");
		return message.toString();
	}
	
	/**
	 * 异常处理
	 */
	private ModelAndView handle(HttpServletRequest request, HttpServletResponse response, CommRsp rsp, Object object) {
		ModelAndView mv = new ModelAndView();
		/* 使用response返回 */
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		//response.setContentType(request.getContentType()); // 设置ContentType
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		JSONObject jsonObject = JSONObject.fromObject(rsp);
		logger.debug(jsonObject.toString());
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
