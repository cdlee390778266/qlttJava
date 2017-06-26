package com.qianlong.qlttms.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.qlttms.domain.TacPoolReqBody;
import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.IIndexSystemService;
import com.qlcd.qltt.body.BppBiz;
import com.qlcd.qltt.body.pvt.T02001001;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02001003;
import com.qlcd.qltt.body.pvt.T02001004;
import com.qlcd.qltt.body.pvt.T02001004._rsp;
import com.qlcd.qltt.body.pvt.T02003001;

@Service
public class IndexSystemServiceImpl implements IIndexSystemService {
	
	private Logger logger = Logger.getLogger(IndexSystemServiceImpl.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public T02001002._rsp queryIdxGroup(String weixinAccountId) {
		logger.debug("原生指标组清单查询");
		T02001002._req.Builder builder = T02001002._req.newBuilder();
		T02001002._rsp rsp = dockService.request(weixinAccountId, "2001002", builder.build());
		return rsp;
	}

	@Override
	public T02001003._rsp queryIdxByGroup(String weixinAccountId, String tacGroup) {
		logger.debug("指定原生指标组成员清单查询");
		T02001003._req.Builder builder = T02001003._req.newBuilder();
		builder.setTacgroup(tacGroup);
		T02001003._rsp rsp = dockService.request(weixinAccountId, "2001003", builder.build());
		return rsp;
	}
	
	@Override
	public T02001001._rsp queryAllIdx(String weixinAccountId) {
		logger.debug("原生指标清单查询");
		T02001001._req.Builder builder = T02001001._req.newBuilder();
		T02001001._rsp rsp = dockService.request(weixinAccountId, "2001001", builder.build());
		return rsp;
	}
	
	@Override
	public T02003001._rsp queryTacDataPool(String weixinAccountId, TacPoolReqBody body) {
		logger.debug("最新实时行情指标数据池查询[分页]");
		T02003001._req.Builder builder = T02003001._req.newBuilder();
		builder.setTactic(body.getTacTic());
		builder.setTacprm(body.getTacPrm());
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(body.getStart());
		pageBuilder.setReqnum(body.getSize());
		builder.setPgreq(pageBuilder.build());
		T02003001._rsp rsp = dockService.request(weixinAccountId, "2003001", builder.build());
		return rsp;
	}

	@Override
	public _rsp queryIdxDefine(String weixinAccountId, String tactic) {
		
		logger.debug("指定原生指标查询[单个]");
		T02001004._req.Builder builder = T02001004._req.newBuilder();
		builder.setTactic(tactic);
		T02001004._rsp rsp = dockService.request(weixinAccountId, "2001004", builder.build());
		return rsp;
	}
}
