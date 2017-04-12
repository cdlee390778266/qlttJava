package com.qianlong.qltt.us.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.exception.QlttRuntimeException;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.protocol.commom.GetAccessTokenReq;
import com.qianlong.qltt.us.protocol.commom.GetAccessTokenRsp;
import com.qianlong.qltt.us.service.IAccessTokenService;

@Controller
@RequestMapping("/token")
public class TokenController extends BaseController{
	
	@Autowired
	private IAccessTokenService accessTokenService;
	/**
	 * 接口名称：获取access_token
	 * 接口请求URL：SERVER-NAME/token/getAccessToken?appid=APPID
	 * 接口请求方式：POST
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"appid":"wx_000001",//渠道id
	 * 		 	"plaintext":"ggggg",//签名明文
	 * 		 	"secret":"secret"//签名
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	    正确的响应格式：
	 * 		{
	 * 			"access_token":"ACCESS_TOKEN",//系统生成的临时访问凭证
	 * 			"product_time":1491805115296,//凭证生成的服务器时间
	 * 			"expires_in":2,//临时访问凭证的有效时间(按小时计算)
	 * 		}
	 *    错误的响应格式：
	 *   	{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="getAccessToken")
	@ResponseBody
	public Object getAccessToken(@Valid @RequestBody GetAccessTokenReq req,BindingResult result,HttpServletRequest request){
		handleReqParamerValid(result);
		try {
			GetAccessTokenRsp rsp = accessTokenService.getAccessToken(getServletContext(request),req);
			return rsp;
		} catch (QlttUSBusinessException e) {
			throw e; 
		}catch (Exception e) {
			throw new QlttRuntimeException(this,e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
