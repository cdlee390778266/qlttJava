package com.qianlong.qltt.us.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.exception.QlttRuntimeException;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac001Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac002Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac003Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Req;
import com.qianlong.qltt.us.service.IAcctAttnTacService;
/**
 * 关注指标Controller
 */
@Controller
@RequestMapping("/acctattntac")
public class UserAcctAttnTacController extends BaseController {
	
	@Autowired
	private IAcctAttnTacService acctAttnTacService;
	
	/**
	 * 接口编号：IFC_UserAcctAttnTac001
	 * 交易码：1004001
	 * 接口名称：新增账户关注指标
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattntac/attntac001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{	
	 * 			"ttacct":"tt000000000001",
	 * 			"attntactic":{
	 * 				"tacprm":1,
	 * 				"tactic":"指标1"
	 * 			}
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
	@RequestMapping(value="attntac001")
	@ResponseBody
	public Object attntac001(@Valid @RequestBody AcctAttnTac001Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			return acctAttnTacService.attntac001(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctAttnTac002
	 * 交易码：1004002
	 * 接口名称：账户取消指标关注
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattntac/attntac002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000000000001",
	 * 			"cncltactic":{
	 * 				"tacprm":1,
	 * 				"tactic":"指标1"
	 * 			}
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
	@RequestMapping(value="attntac002")
	@ResponseBody
	public Object attntac002(@Valid @RequestBody AcctAttnTac002Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			return acctAttnTacService.attntac002(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctAttnTac003
	 * 交易码：1004003
	 * 接口名称：账户关注指标变更
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattntac/attntac003?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000000000001",//推推账号
	 * 			"attntactic":[//关注指标列表
	 * 				{
	 * 					"tacprm":2,//指标参数
	 * 					"tactic":"指标2"//指标
	 * 				},
	 * 				{
	 * 					"tacprm":1,
	 * 					"tactic":"指标1"
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
	@RequestMapping(value="attntac003")
	@ResponseBody
	public Object attntac003(@Valid @RequestBody AcctAttnTac003Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			return acctAttnTacService.attntac003(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	

	/**
	 * 接口编号：IFC_UserAcctAttnTac004
	 * 交易码：1004004
	 * 接口名称：账户关注指标查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctattntac/attntac004?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000000000001"//推推账号	
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"attntactic":[//关注指标列表
	 * 				{
	 * 					"tacprm":2,//指标参数
	 * 					"tactic":"指标2"//指标
	 * 				},
	 * 				{
	 * 					"tacprm":1,
	 * 					"tactic":"指标1"
	 * 				}]
	 * 		}
	 * 	错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="attntac004")
	@ResponseBody
	public Object attntac004(@Valid @RequestBody AcctAttnTac004Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			return acctAttnTacService.attntac004(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
}
