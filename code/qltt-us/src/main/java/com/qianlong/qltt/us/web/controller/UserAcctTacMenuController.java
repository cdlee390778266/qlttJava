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
import com.qianlong.qltt.us.protocol.TtacctPageReq;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu001Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu002Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu003Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu004Rsp;
import com.qianlong.qltt.us.service.IAcctTacMenuService;
import com.qianlong.qltt.us.util.JSONUtil;

/**
 * 指标菜单Controller
 */
@Controller
@RequestMapping("/accttacmenu")
public class UserAcctTacMenuController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(UserAcctTacMenuController.class);
	
	@Autowired
	private IAcctTacMenuService acctTacMenuService;
	
	/**
	 * 接口编号：IFC_UserAcctTacMenu001
	 * 交易码：1007001
	 * 接口名称：新增账户组合指标菜单
	 * 接口描述：
	 * 备注说明：>.用于单例新增
	 * 接口请求URL：SERVER-NAME/accttacmenu/tacmenu001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 *  	{
	 *  		"ttacct":"tt000001",
	 *  		"addtacmenu":{
	 *  			"tactic":"指标1",
	 *  			"tacname":"指标名字1",
	 *  			"tacdetail":"指标内容1"
	 *  		}
	 *  	}
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
	@RequestMapping(value="tacmenu001")
	@ResponseBody
	public Object attnstock001(@Valid @RequestBody AcctTacMenu001Req req,BindingResult result){
		logger.debug("协议号：1007001");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		handleReqParamerValid(result);
		try {
			return acctTacMenuService.tacmenu001(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	
	/**
	 * 接口编号：IFC_UserAcctTacMenu002
	 * 交易码：1007002
	 * 接口名称：删除账户组合指标菜单
	 * 接口描述：
	 * 备注说明：>.用于单例删除
	 * 接口请求URL：SERVER-NAME/accttacmenu/tacmenu002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 *	 	{
	 * 			"ttacct":"tt000001",
	 * 			"deltacmenu":{
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
	@RequestMapping(value="tacmenu002")
	@ResponseBody
	public Object attnstock002(@Valid @RequestBody AcctTacMenu002Req req,BindingResult result){
		logger.debug("协议号：1007002");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		handleReqParamerValid(result);
		try {
			return acctTacMenuService.tacmenu002(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctTacMenu003
	 * 交易码：1007003
	 * 接口名称：账户组合指标菜单变更
	 * 接口描述：
	 * 备注说明：>.用于批量操作
	 * 接口请求URL：SERVER-NAME/accttacmenu/tacmenu003?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"addtacmenu":[{
	 * 				"order":2,
	 * 				"tactic":"指标2",
	 * 				"tacname":"指标名字2",
	 * 				"tacdetail":"指标描述2"
	 * 			}]
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
	@RequestMapping(value="tacmenu003")
	@ResponseBody
	public Object attnstock003(@Valid @RequestBody AcctTacMenu003Req req,BindingResult result){
		logger.debug("协议号：1007003");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		handleReqParamerValid(result);
		try {
			return acctTacMenuService.tacmenu003(req);
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	/**
	 * 接口编号：IFC_UserAcctTacMenu004
	 * 交易码：1007004
	 * 接口名称：账户组合指标菜单查询
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/accttacmenu/tacmenu004?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"tt000001",
	 * 			"pagereq":{
	 * 				"reqnum":10,
	 * 				"reqstart":0
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
	@RequestMapping(value="tacmenu004")
	@ResponseBody
	public Object attnstock004(@Valid @RequestBody TtacctPageReq req,BindingResult result){
		logger.debug("协议号：1007004");
		logger.debug("协议传入参数："+JSONUtil.objToJson(req));
		handleReqParamerValid(result);
		try {
			AcctTacMenu004Rsp rsp = acctTacMenuService.tacmenu004(req);
			logger.debug("协议返回结果："+JSONUtil.objToJson(rsp));
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
}

