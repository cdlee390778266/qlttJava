package com.qianlong.webapp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.domain.SrcIndexReqBody;
import com.qianlong.webapp.exception.BusinessException;
import com.qianlong.webapp.service.ICombinedIndexService;
import com.qlcd.qltt.body.pvt.T02002001;
import com.qlcd.qltt.body.pvt.T02002002;
import com.qlcd.util.ZMQProxyClient;

@Service
public class CombinedIndexServiceImpl implements ICombinedIndexService {
	
	private Logger logger = Logger.getLogger(CombinedIndexServiceImpl.class);
	
	@Autowired
	private ZMQProxyClient zmqProxyClient;

	@Override
	public String addComIndex(List<SourceIndex> indices) {
		logger.debug("新增组合指标");
		T02002001._req.Builder builder = T02002001._req.newBuilder();
		if (CollectionUtils.isEmpty(indices))
			throw new BusinessException("新增组合指标时必须指定所需的原生指标！");
		for (SourceIndex index : indices) {
			builder.addCbelist(T02002001._combele.newBuilder().setSrctactic(index.getSrcTactic()).setSrctacticprm(index.getSrcTacticPrm()));
		}
		
		T02002001._rsp rsp = zmqProxyClient.outBound("2002001", builder.build());
		if (rsp == null)
			throw new BusinessException("没有得到新增组合指标的编号！");
		return rsp.getTactic();
	}

	@Override
	public T02002002._rsp queryComIndex(SrcIndexReqBody req) {
		return null;
	}

}
