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
import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Req;
import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Rsp;
import com.qianlong.qltt.us.service.IAcctOpenService;
/**
 * 渠道开户管理Controller
 */
@Controller
@RequestMapping("/acctopen")
public class UserAcctOpenController extends BaseController {
	
	@Autowired
	private IAcctOpenService acctOpenService;
	
	/**
	 * 接口编号：IFC_UserAcctOpen001
	 * 交易码：1001001
	 * 接口名称：账号开通(手机号识别)
	 * 接口描述：根据手机号开通唯一用户账号
	 * 备注说明：账号开通采用手机认证模式，渠道认证服务器上认证通过过后才上送本协议
	 * 接口请求URL：SERVER-NAME/acctopen/open001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"cn":"13730894567",//手机号
	 * 			"acctbindinfo":{//渠道账号绑定信息,可为空
	 * 				"svcchnl":1,//服务渠道
	 * 				"bindacct":"1234567890123456789012345678"//渠道绑定账号
	 * 			}
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式：
	 * 		{
	 * 			"cn":"13735263215",//推推账号
	 * 			"ttacct":"qltt00000001"//手机号
	 * 		}
	 * 
	 * 	错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="open001")
	@ResponseBody
	public Object open001(@Valid @RequestBody AcctOpen001Req req,BindingResult result){
		handleReqParamerValid(result);
		try {
			AcctOpen001Rsp rsp = acctOpenService.open001(req);
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
}
