package com.qianlong.qltt.us.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.domain.token.GetAccessTokenReq;
import com.qianlong.qltt.us.domain.token.GetAccessTokenRsp;
import com.qianlong.qltt.us.service.IAccessTokenService;

@Controller
@RequestMapping("/token")
public class TokenController extends BaseController{
	
	@Autowired
	private IAccessTokenService accessTokenService;
	/**
	 * 获取access_token
	 * 请求Json数据格式:
	 * 		{"appid":"wx_000001","plaintext":"ggggg","secret":"secret"}
	 * 响应JSon数据格式：
	 * 		{"access_token":"ACCESS_TOKEN","expires_in":7200}
	 * 		access_token:系统生成的临时凭证
	 * 		expires_in:该凭证的有效时间
	 */
	@RequestMapping(value="getAccessToken")
	@ResponseBody
	public Object getAccessToken(@Valid @RequestBody GetAccessTokenReq req,BindingResult result,HttpServletRequest request){
		handleReqParamerValid(result);
		GetAccessTokenRsp rsp = accessTokenService.getAccessToken(getServletContext(request),req);
		return rsp;
	}
}
