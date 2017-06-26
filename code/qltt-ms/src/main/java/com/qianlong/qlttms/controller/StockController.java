package com.qianlong.qlttms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.BaseIndex;
import com.qianlong.qlttms.domain.QueryStockPoolIndexsRspEntity;
import com.qianlong.qlttms.domain.TacPoolReqBody;
import com.qianlong.qlttms.service.ICombinedIndexService;
import com.qianlong.qlttms.service.IIndexSystemService;
import com.qianlong.qlttms.service.IMyAttentionService;
import com.qianlong.qlttms.service.IUserStockService;
import com.qianlong.qlttms.utils.Constants;
import com.qlcd.qltt.body.pvt.T02001004;
import com.qlcd.qltt.body.pvt.T02002003;
import com.qlcd.qltt.body.pvt.T02003001;
import com.qlcd.qltt.body.pvt.T02003001._dprealtime;
import com.qlcd.qltt.body.pvt.T02005001;
import com.qlcd.qltt.body.pvt.T02005001._dpcomb;

/**
 * 股票查询
 * 
 * @author wangk
 */
@Controller
@RequestMapping("webapp/stock")
public class StockController {

	private Logger logger = Logger.getLogger(StockController.class);

	@Autowired
	private IIndexSystemService indexSystemService;

	@Autowired
	private IMyAttentionService myAttentionService;

	@Autowired
	private ICombinedIndexService combinedIndexService;

	@Autowired
	private IUserStockService userStockService;

	@RequestMapping("home")
	public ModelAndView home(@RequestParam(value = "tactic") String tacTic,
			@RequestParam(value = "tacname") String tacName,
			@RequestParam(value = "isCombRequest", required = false) Boolean isCombRequest,
			HttpServletRequest request) {
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		Map<String, Object> model = new HashMap<>();

		BaseIndex index = new BaseIndex();
		index.setTacTic(tacTic);
		index.setTacPrm(0);
		boolean isFollow = myAttentionService
				.isFollow(user.getWeixinAccountId(), index, user.getTtacct());

		if (isCombRequest != null && isCombRequest) {
			T02002003._rsp rsp = combinedIndexService
					.querycCombTacticMebs(user.getWeixinAccountId(), tacTic);
			model.put("members", rsp.getCbelistList());
		}
		model.put("isCombRequest", isCombRequest != null && isCombRequest);//是否为
		model.put("tacTic", tacTic);
		model.put("tacName", tacName);
		model.put("isFollow", isFollow);
		return new ModelAndView("qianlong/stock", model);
	}
	
	
	@RequestMapping("indexinfo")
	@ResponseBody
	public Object indexinfo(HttpServletRequest request, 
			@RequestParam(value = "tactic") String tactic,
			@RequestParam(value = "isCombRequest", required = false) Boolean isCombRequest) {
		
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		
		Map<String, Object> model = new HashMap<>();
		
		BaseIndex index = new BaseIndex();
		index.setTacTic(tactic);
		index.setTacPrm(0);
		boolean isFollow = myAttentionService
				.isFollow(user.getWeixinAccountId(), index, user.getTtacct());
		model.put("isFollow", isFollow);
		
		T02001004._rsp rsp1004 = indexSystemService.queryIdxDefine(user.getWeixinAccountId(),  tactic);
		model.put("tacName", rsp1004.getTacname());
		model.put("tacDetail", rsp1004.getTacdetail());
		
		if (isCombRequest != null && isCombRequest) {
			T02002003._rsp rsp2003 = combinedIndexService
					.querycCombTacticMebs(user.getWeixinAccountId(), tactic);
			try {
				model.put("members",   JSONObject.parse(JsonFormat.printer().includingDefaultValueFields().print(rsp2003)));
			} catch (InvalidProtocolBufferException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return model;
		
	}
	
	

	@RequestMapping("pool")
	@ResponseBody
	public Object pool(HttpServletRequest request, TacPoolReqBody body) {
		logger.debug("最新实时行情指标数据池查询[分页]");
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		T02003001._rsp rsp = indexSystemService.queryTacDataPool(user.getWeixinAccountId(), body);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("pgrsp", JSONObject.parse(JsonFormat.printer()
					.includingDefaultValueFields().print(rsp.getPgrsp())));
			List<Object> dprtlist = new ArrayList<Object>();
			map.put("dprtlist", dprtlist);
			List<_dprealtime> _dprealtimes = rsp.getDprtlistList();
			if (!CollectionUtils.isEmpty(_dprealtimes)) {
				QueryStockPoolIndexsRspEntity poolindexs = null;
				Map<String, Object> dprealtimeMap = null;
				for (_dprealtime dprealtime : _dprealtimes) {
					dprealtimeMap = new HashMap<String, Object>();
					String stockcode = dprealtime.getStockcode();
					poolindexs = userStockService.queryStockPoolIndexs(
							user.getWeixinAccountId(), stockcode,
							user.getTtacct());
					dprealtimeMap.put("detail",
							JSONObject.parse(JsonFormat.printer()
									.includingDefaultValueFields()
									.print(dprealtime)));
					dprealtimeMap.put("isSelected", !CollectionUtils
							.isEmpty(poolindexs.getExistpools()));
					dprtlist.add(dprealtimeMap);
				}
			}
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	/**
	 * 组合指标池查询
	 */
	@RequestMapping("combpool")
	@ResponseBody
	public Object combPool(HttpServletRequest request, TacPoolReqBody body) {
		logger.debug("组合指标数据池查询[分页]");
		AuthResultEntity user = (AuthResultEntity) request.getSession().getAttribute(Constants.LOGIN_USER_ACCOUNT);
		T02005001._rsp rsp = combinedIndexService.queryCombPool(user.getWeixinAccountId(), body);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("pgrsp",
					JSONObject.parse(JsonFormat.printer().includingDefaultValueFields().print(rsp.getPgrsp())));
			List<Object> dprtlist = new ArrayList<Object>();
			map.put("dprtlist", dprtlist);
			List<_dpcomb> _dpcombs = rsp.getDpcblistList();
			if (!CollectionUtils.isEmpty(_dpcombs)) {
				QueryStockPoolIndexsRspEntity poolindexs = null;
				Map<String, Object> dprealtimeMap = null;
				for (_dpcomb dpcomb : _dpcombs) {
					dprealtimeMap = new HashMap<String, Object>();
					String stockcode = dpcomb.getStockcode();
					poolindexs = userStockService.queryStockPoolIndexs(user.getWeixinAccountId(), stockcode, user.getTtacct());
					dprealtimeMap.put("detail",
							JSONObject.parse(JsonFormat.printer().includingDefaultValueFields().print(dpcomb)));
					dprealtimeMap.put("isSelected", !CollectionUtils.isEmpty(poolindexs.getExistpools()));
					dprtlist.add(dprealtimeMap);
				}
			}
		} catch (InvalidProtocolBufferException e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	@RequestMapping("poolindexs")
	@ResponseBody
	public Object getPoolIndexs(HttpServletRequest request, String stockcode) {
		logger.debug("查询一只股票处于那些股票池");
		AuthResultEntity user = (AuthResultEntity) request.getSession()
				.getAttribute(Constants.LOGIN_USER_ACCOUNT);
		QueryStockPoolIndexsRspEntity poolindexs = userStockService
				.queryStockPoolIndexs(user.getWeixinAccountId(), stockcode,
						user.getTtacct());
		return poolindexs.getExistpools();
	}
}
