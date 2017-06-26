package com.qianlong.qlttms.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qianlong.qlttms.domain.FilterBody;
import com.qianlong.qlttms.domain.SourceIndex;
import com.qianlong.qlttms.domain.SrcIndexReqBody;
import com.qianlong.qlttms.domain.TacPoolReqBody;
import com.qianlong.qlttms.exception.BusinessException;
import com.qianlong.qlttms.service.ICombinedIndexService;
import com.qianlong.qlttms.service.IDockService;
import com.qlcd.qltt.body.BppBiz;
import com.qlcd.qltt.body.pvt.T02002001;
import com.qlcd.qltt.body.pvt.T02002002;
import com.qlcd.qltt.body.pvt.T02002003;
import com.qlcd.qltt.body.pvt.T02005001;
import com.qlcd.qltt.body.pvt.T02005002;

@Service
public class CombinedIndexServiceImpl implements ICombinedIndexService {
	
	private Logger logger = Logger.getLogger(CombinedIndexServiceImpl.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public String addComIndex(String weixinAccountId, List<SourceIndex> indices) {
		logger.debug("新增组合指标");
		T02002001._req.Builder builder = T02002001._req.newBuilder();
		if (CollectionUtils.isEmpty(indices))
			throw new BusinessException("新增组合指标时必须指定所需的原生指标！");
		for (SourceIndex index : indices) {
			builder.addCbelist(T02002001._combele.newBuilder().setSrctactic(index.getSrcTactic()).setSrctacticprm(index.getSrcTacticPrm()));
		}
		T02002001._rsp rsp = dockService.request(weixinAccountId, "2002001", builder.build());
		if (rsp == null)
			throw new BusinessException("没有得到新增组合指标的编号！");
		return rsp.getTactic();
	}

	@Override
	public T02002002._rsp queryComIndex(String weixinAccountId, SrcIndexReqBody req) {
		logger.debug("组合指标清单查询");
		T02002002._req.Builder builder = T02002002._req.newBuilder();
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(req.getStart());
		pageBuilder.setReqnum(req.getSize());
		builder.setPgreq(pageBuilder.build());
		T02002002._rsp rsp = dockService.request(weixinAccountId, "2002002", builder.build());
		return rsp;
	}
	
	@Override
	public T02002003._rsp querycCombTacticMebs(String weixinAccountId, String tacTic) {
		logger.debug("组合指标构成查询");
		T02002003._req.Builder builder = T02002003._req.newBuilder();
		builder.setTactic(tacTic);
		T02002003._rsp rsp = dockService.request(weixinAccountId, "2002003", builder.build());
		return rsp;
	}
	
	@Override
	public T02005001._rsp queryCombPool(String weixinAccountId, TacPoolReqBody body) {
		logger.debug("指标组合查询[分页]");
		T02005001._req.Builder builder = T02005001._req.newBuilder();
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(body.getStart());
		pageBuilder.setReqnum(body.getSize());
		
		if (StringUtils.isEmpty(body.getTacTic()))
			throw new BusinessException("请指定指标查询！");
		builder.setTactic(body.getTacTic());
		builder.setPgreq(pageBuilder);
		T02005001._rsp rsp = dockService.request(weixinAccountId, "2005001", builder.build());
		return rsp;
	}

	@Override
	public T02005002._rsp filtration(String weixinAccountId, FilterBody filter) {
		logger.debug("指标组合查询[分页]");
		T02005002._req.Builder builder = T02005002._req.newBuilder();
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(filter.getStart());
		pageBuilder.setReqnum(filter.getSize());
		if (CollectionUtils.isEmpty(filter.getIndices()))
			throw new BusinessException("请指定指标查询！");
		for (SourceIndex index : filter.getIndices()) {
			builder.addTclist(T02005002._taccomb.newBuilder().setTactic(index.getSrcTactic()).setTacprm(index.getSrcTacticPrm()));
		}
		
		builder.setPgreq(pageBuilder);
		T02005002._rsp rsp = dockService.request(weixinAccountId, "2005002", builder.build());
		return rsp;
	}


}
