package com.qianlong.qltt.us.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin001Req;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin002Req;
import com.qianlong.qltt.us.service.IAcctLoginService;

/**
 * 登陆管理Controller
 */
@Controller
@RequestMapping("/acctlogin")
public class UserAcctLoginController extends BaseController {
	
	@Autowired
	private IAcctLoginService acctLoginService;
	
	/**
	 * 接口编号：IFC_UserAcctLogin001
	 * 交易码：1002001
	 * 接口名称：账号登录(手机号登录)
	 * 接口描述：根据手机号登录
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctlogin/login001?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"cn":"13730894567"//手机号码
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	  正确的响应格式
	 * 		{
	 * 			"cn":"13735263215",//推推账号
	 * 			"ttacct":"qltt00000001"//手机号
	 * 		}
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="login001")
	@ResponseBody
	public Object login001(@Valid @RequestBody AcctLogin001Req req,BindingResult result){
		handleReqParamerValid(result);
		return  acctLoginService.login001(req);
	}
	
	/**
	 * 接口编号：IFC_UserAcctLogin002
	 * 交易码：1002002
	 * 接口名称：账号登录(第三方验证授信登录)
	 * 接口描述：根据第三方授信账号登录
	 * 备注说明：
	 * 接口请求URL：SERVER-NAME/acctlogin/login002?access_token=ACCESSTOKEN
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"svcchnl":1,//服务渠道
	 * 			"bindacct":"01234567890123456789012345678"//渠道绑定账号
	 * 		}
	 * 接口响应JSon数据格式：
	 *  正确的响应格式：
	 * 		{
	 * 			"cn":"13735263215",//推推账号
	 * 			"ttacct":"qltt00000001"//手机号
	 * 		}
	 * 	 错误的响应格式：
	 * 		{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="login002")
	@ResponseBody
	public Object login002(@Valid @RequestBody AcctLogin002Req req,BindingResult result){
		handleReqParamerValid(result);
		return acctLoginService.login002(req);
	}
}
