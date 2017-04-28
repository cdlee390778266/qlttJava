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

import com.qianlong.qltt.us.exception.QlttRuntimeException;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Rsp;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStockCommReq;
import com.qianlong.qltt.us.service.IAcctAttnStockService;
import com.qianlong.qltt.us.util.JSONUtil;
/**
 * 关注指标Controller
 */
@Controller
@RequestMapping("/acctattnstock")
public class UserAcctAttnStockController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(UserAcctAttnStockController.class);
	
	@Autowired
	private IAcctAttnStockService acctAttnStockService;
	
	/**
	 * 接口编号：IFC_UserAcctAttnStock001
	 * 交易码：1005001
	 * 接口名称：新增账户关注个股
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattnstock/attnstock001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"stockpool":[
	 * 				{
	 * 					"poolindex":1,
	 * 					"attnstock":[
	 * 						{
	 * 							"stockorder":1,
	 * 							"stockcode":"TJ001",
	 * 							"stockname":"添加股票1"
	 * 						},
	 * 						{
	 * 							"stockorder":2,
	 * 							"stockcode":"TJ002",
	 * 							"stockname":"添加股票2"
	 * 						}]
	 * 				}]
	 * 		}
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
	@RequestMapping(value="attnstock001")
	@ResponseBody
	public Object attnstock001(@Valid @RequestBody AcctAttnStockCommReq req,BindingResult result){
		logger.debug("协议号：1005001");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		handleReqParamerValid(result);
		try {
			return acctAttnStockService.attnstock001(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctAttnStock002
	 * 交易码：1005002
	 * 接口名称：取消账户个股关注
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattnstock/attnstock002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 	 	{
	 * 			"ttacct":"tt000001",
	 * 			"stockpool":[
	 * 				{
	 * 					"poolindex":1,
	 * 					"cnclstock":[
	 * 						{
	 * 							"stockcode":"SC001",
	 * 							"stockname":"删除股票1"
	 * 						},
	 * 						{
	 * 							"stockcode":"SC002",
	 * 							"stockname":"删除股票2"
	 * 						}]
	 * 				}]
	 * 		}
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
	@RequestMapping(value="attnstock002")
	@ResponseBody
	public Object attnstock002(@Valid @RequestBody AcctAttnStockCommReq req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1005002");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		try {
			return acctAttnStockService.attnstock002(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctAttnStock003
	 * 交易码：1005003
	 * 接口名称：账户关注个股变更
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattnstock/attnstock003?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"stockpool":[
	 * 				{
	 * 					"poolindex":1,
	 * 					"attnstock":[
	 * 						{
	 * 							"stockorder":1,
	 * 							"stockcode":"TJ001",
	 * 							"stockname":"添加股票1"
	 * 						},
	 * 						{
	 * 							"stockorder":2,
	 * 							"stockcode":"TJ002",
	 * 							"stockname":"添加股票2"
	 * 						}]
	 * 				}]
	 * 		}
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
	@RequestMapping(value="attnstock003")
	@ResponseBody
	public Object attnstock003(@Valid @RequestBody AcctAttnStockCommReq req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1005003");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		try {
			return acctAttnStockService.attnstock003(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	

	/**
	 * 接口编号：IFC_UserAcctAttnStock004
	 * 交易码：1005004
	 * 接口名称：账户指定指标池关注个股查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattnstock/attnstock004?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"pagereq":
	 * 				{
	 * 					"reqnum":10,
	 * 					"reqstart":0
	 * 				},
	 * 			"ttacct":"tt000001",
	 * 			"poolindex":1
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"pagersp":
	 * 				{
	 * 					"rspnum":10,
	 * 					"totalnum":29
	 * 				},
	 * 			"attnstock":
	 * 				[{
	 * 					"stockcode":"6100000",
	 * 					"stockname":"中国石化"
	 * 				},
	 * 				{
	 * 					"stockcode":"6100001",
	 * 					"stockname":"中国石油"
	 * 				}]
	 * 		}
	 * 	错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 **/
	@RequestMapping(value="attnstock004")
	@ResponseBody
	public Object attnstock004(@Valid @RequestBody AcctAttnStock004Req req,BindingResult result){
		handleReqParamerValid(result);
		logger.debug("协议号：1005004");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		try {
			AcctAttnStock004Rsp rsp = acctAttnStockService.attntac004(req);
			logger.debug("协议返回参数："+JSONUtil.objToJson(rsp));
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
}
