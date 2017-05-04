package com.qianlong.qltt.us.web.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianlong.qltt.us.protocol.app.TokenReset001;
import com.qianlong.qltt.us.service.IAppAdminService;

@Controller
@RequestMapping("/app")
public class AppAdminController extends BaseController{
	
	@Autowired
	private IAppAdminService appAdminService;
	/**
	 * 接口名称：获取access_token
	 * 接口请求URL：SERVER-NAME/app/createApp
	 * 接口请求方式：GET
	 * 接口响应JSon数据格式：
	 * 	    正确的响应格式：
	 * 		{
	 * 			"appid":"app000000000001",//渠道编号
	 * 			"token":"app000000000001",//凭证生成的服务器时间
	 * 			"secret":"CLlTNP394PISXMTPIcYNRvgkIPb9j/DRZ+M7sUp9gCL92t5sIDpprHHeOWoaZnL3C3qXeF1FOYybseLvxohcWqnw2QrIN8VzXFe9TBuF3sdY1wMqxtXKb/i61EJHwsjERYGTY/iI7y1Zl3VwavbNC02IXL+0vPIGosnyQ12tw0M="//签名
	 * 		}
	 *    错误的响应格式：
	 *   	{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="createApp")
	@ResponseBody
	public Object createApp(){
		return appAdminService.createApp();
	}
	
	/**
	 * 接口名称：重置Token
	 * 接口请求URL：SERVER-NAME/app/resetAppToken?appid=APPID
	 * 接口请求方式：post
	 * 接口请求Json数据格式:
	 * 		{
	 * 			"appid":"app000000000001",//渠道编号
	 * 			"old_token":"old_token",//旧的Token
	 * 			"new_token":"new_token",//新的Token
	 * 			"secret":"CLlTNP394PISXMTPIcYNRvgkIPb9j/DRZ+M7sUp9gCL92t5sIDpprHHeOWoaZnL3C3qXeF1FOYybseLvxohcWqnw2QrIN8VzXFe9TBuF3sdY1wMqxtXKb/i61EJHwsjERYGTY/iI7y1Zl3VwavbNC02IXL+0vPIGosnyQ12tw0M="//签名
	 * 		}
	 * 接口响应JSon数据格式：
	 * 	    正确的响应格式：
	 * 		{
	 * 			"appid":"app000000000001",//渠道编号
	 * 			"old_token":"old_token",//旧的Token
	 * 			"new_token":"new_token",//新的Token
	 * 			"secret":"CLlTNP394PISXMTPIcYNRvgkIPb9j/DRZ+M7sUp9gCL92t5sIDpprHHeOWoaZnL3C3qXeF1FOYybseLvxohcWqnw2QrIN8VzXFe9TBuF3sdY1wMqxtXKb/i61EJHwsjERYGTY/iI7y1Zl3VwavbNC02IXL+0vPIGosnyQ12tw0M="//签名
	 * 		}
	 *    错误的响应格式：
	 *   	{
	 * 			"errorCode":"00010001",
	 * 			"errorMsg":"请求参数格式不正确"
	 * 		}
	 */
	@RequestMapping(value="resetAppToken")
	@ResponseBody
	public Object resetAppToken(@Valid @RequestBody TokenReset001 req,BindingResult result){
		handleReqParamerValid(result);
		return  appAdminService.resetAppToken(req);
	}
	
	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
}
