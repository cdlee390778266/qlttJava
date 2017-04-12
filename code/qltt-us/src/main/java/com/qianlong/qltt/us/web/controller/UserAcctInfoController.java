package com.qianlong.qltt.us.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.exception.QlttRuntimeException;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo001Req;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo002Req;
import com.qianlong.qltt.us.service.IAcctInfoService;
/**
 * 信息维护管理Controller
 */
@Controller
@RequestMapping("/acctinfo")
public class UserAcctInfoController extends BaseController {
	
	@Autowired
	private IAcctInfoService acctInfoService;
	
	/**
	 * 接口编号：IFC_UserAcctInfo001
	 * 交易码：1003001
	 * 接口名称：账号基本信息修改
	 * 接口描述：
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctinfo/info001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"ttacct":"qltt00000001",//推推账号
	 * 			"name":"AHQ"//账号名称
	 * 		}
	 * 接口响应JSon数据格式：
	 * 		{
	 * 			"errorCode":"0",
	 * 			"errorMsg":"ok"
	 * 		}
	 */
	@RequestMapping(value="info001")
	@ResponseBody
	public Object info001(@Valid @RequestBody AcctInfo001Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			CommRsp rsp = acctInfoService.info001(req);
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	
	}
	
	/**
	 * 接口编号：IFC_UserAcctInfo002
	 * 交易码：1003002
	 * 接口名称：手机设备号更新
	 * 接口描述：针对手机App适用
	 * 备注说明：账号在手机App上登录成功后紧接发送该协议
	 * 接口请求URL：SERVER-NAME/acctinfo/info002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"devno":"XXXXXXXXXXXXXX",//设备号
	 * 			"devtype":1,//设备类型
	 * 			"ttacct":"qltt00000001"//推推账号
	 * 		}
	 * 接口响应JSon数据格式：
	 * 		{
	 * 			"errorCode":"0",
	 * 			"errorMsg":"ok"
	 * 		}
	 */
	@RequestMapping(value="info002")
	@ResponseBody
	public Object info002(@Valid @RequestBody AcctInfo002Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			CommRsp rsp = acctInfoService.info002(req);
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
}
