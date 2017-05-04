package com.qianlong.qltt.us.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushFreq;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushNum;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushScope;
import com.qianlong.qltt.us.service.IAcctSetPrmService;
import com.qianlong.qltt.us.util.JSONUtil;
/**
 * 参数设置Controller
 */
@Controller
@RequestMapping("/acctsetprm")
public class UserAcctSetPrmController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(UserAcctSetPrmController.class);
	
	@Autowired
	private IAcctSetPrmService acctSetPrmService;
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm001
	 * 交易码：1006001
	 * 接口名称：全局推送范围设置
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushscope":1,
	 * 			"scopeprm":1
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"errorCode":"0",
	 * 			"errorMsg":"OK"
	 * 		}
	 * 	错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm001")
	@ResponseBody
	public Object setprm001(@Valid @RequestBody SetPrmPushScope req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006001");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		return acctSetPrmService.setprm001(req);
	}
	
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm002
	 * 交易码：1006002
	 * 接口名称：全局推送范围查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001"
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushscope":1,
	 * 			"scopeprm":1
	 *		 }
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm002")
	@ResponseBody
	public Object setprm002(@Valid @RequestBody Ttacct req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006002");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		SetPrmPushScope rsp = acctSetPrmService.setprm002(req);
		logger.debug("协议返回数据："+JSONUtil.objToJson(rsp));
		return rsp;
	}
	
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm003
	 * 交易码：1006003
	 * 接口名称：全局推送数量设置
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm003?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushnum":1000
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 *		{
	 * 			"errorCode":"0",
	 * 			"errorMsg":"OK"
	 * 		}
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm003")
	@ResponseBody
	public Object setprm003(@Valid @RequestBody SetPrmPushNum req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006003");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		return acctSetPrmService.setprm003(req);
	}
	
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm004
	 * 交易码：1006004
	 * 接口名称：全局推送数量查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm004?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001"
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushnum":1000
	 *		 }
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm004")
	@ResponseBody
	public Object setprm004(@Valid @RequestBody Ttacct req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006004");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		SetPrmPushNum rsp = acctSetPrmService.setprm004(req);
		logger.debug("协议返回数据："+JSONUtil.objToJson(rsp));
		return rsp;
		
	}
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm005
	 * 交易码：1006005
	 * 接口名称：全局推送频率设置
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm005?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushfreq",1999
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 *		{
	 * 			"errorCode":"0",
	 * 			"errorMsg":"OK"
	 * 		}
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm005")
	@ResponseBody
	public Object setprm005(@Valid @RequestBody SetPrmPushFreq req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006005");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		CommRsp rsp = acctSetPrmService.setprm005(req);
		logger.debug("协议返回数据："+JSONUtil.objToJson(rsp));
		return rsp;
		
	}
	
	/**
	 * 接口编号：IFC_UserAcctSetPrm006
	 * 交易码：1006006
	 * 接口名称：全局推送频率查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctsetprm/setprm006?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001"
	 *		 }
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	* 		{
	 * 			"ttacct":"tt000001",
	 * 			"pushfreq",1999
	 *		 }
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="setprm006")
	@ResponseBody
	public Object setprm006(@Valid @RequestBody Ttacct req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1006006");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		SetPrmPushFreq rsp = acctSetPrmService.setprm006(req);
		logger.debug("协议返回数据："+JSONUtil.objToJson(rsp));
		return rsp;
	}
}
