package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.domain.SrcIndexReqBody;
import com.qlcd.qltt.body.pvt.T02002002;

public interface ICombinedIndexService {

	public String addComIndex(List<SourceIndex> indices);
	
	public T02002002._rsp queryComIndex(SrcIndexReqBody req);
}
