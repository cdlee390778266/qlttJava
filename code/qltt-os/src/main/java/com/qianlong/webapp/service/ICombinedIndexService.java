package com.qianlong.webapp.service;

import java.util.List;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.domain.SrcIndexReqBody;
import com.qlcd.qltt.body.pvt.T02002002;

/**
 * 组合指标
 * @author wangk
 */
public interface ICombinedIndexService {

	/**
	 * <p>接口编号：IFC_TacCombTacticDefine001
	 * <p>交易码：2002001
	 * <p>接口名称：新增组合指标
	 * 
	 * @return 组合指标编号
	 */
	public String addComIndex(List<SourceIndex> indices);
	
	/**
	 * <p>接口编号：IFC_TacCombTacticDefine002
	 * <p>交易码：2002002
	 * <p>接口名称：组合指标清单查询
	 * 
	 * @return 组合指标列表
	 */
	public T02002002._rsp queryComIndex(SrcIndexReqBody req);
}
