package com.qianlong.webapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.webapp.domain.TacPoolReqBody;
import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.pvt.T02003001;

/**
 * 股票查询
 * @author wangk
 */
@Controller
@RequestMapping("webapp/stock")
public class StockController {

private Logger logger = Logger.getLogger(StockController.class);
	
	@Autowired
	private IIndexSystemService indexSystemService;

	@RequestMapping("home")
	public ModelAndView home(@RequestParam(value = "tactic")String tacTic, @RequestParam(value = "tacname")String tacName) {
		Map<String, Object> model = new HashMap<>();
		model.put("tacTic", tacTic);
		model.put("tacName", tacName);
		return new ModelAndView("qianlong/stock", model);
	}
	
	@RequestMapping("pool")
	@ResponseBody
	public Object pool(TacPoolReqBody body) {
		logger.debug("最新实时行情指标数据池查询[分页]");
		T02003001._rsp rsp = indexSystemService.queryTacDataPool(body);
		
		Object result = null;
		try {
			String json = JsonFormat.printer().print(rsp);
			logger.debug(String.format("T02003001 交易 - 转换后的JSON字符串: [%s]", json));
			JsonFormat.printer().print(rsp);
			result = JSONObject.parse(json);
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
}
