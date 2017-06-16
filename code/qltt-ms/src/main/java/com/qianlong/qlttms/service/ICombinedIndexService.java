package com.qianlong.qlttms.service;

import java.util.List;

import com.qianlong.qlttms.domain.FilterBody;
import com.qianlong.qlttms.domain.SourceIndex;
import com.qianlong.qlttms.domain.SrcIndexReqBody;
import com.qlcd.qltt.body.pvt.T02002002;
import com.qlcd.qltt.body.pvt.T02002003;
import com.qlcd.qltt.body.pvt.T02005002;

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
	public String addComIndex(String weixinAccountId, List<SourceIndex> indices);
	
	/**
	 * <p>接口编号：IFC_TacCombTacticDefine002
	 * <p>交易码：2002002
	 * <p>接口名称：组合指标清单查询
	 * 
	 * @return 组合指标列表
	 */
	public T02002002._rsp queryComIndex(String weixinAccountId, SrcIndexReqBody req);
	
	/**
	 * <p>接口编号：IFC_TacCombTacticDefine003
	 * <p>交易码：2002003
	 * <p>接口名称：组合指标构成查询
	 * @return 组合指标成员
	 */
	public T02002003._rsp querycCombTacticMebs(String weixinAccountId, String tacTic);
	
	/**
	 * <p>接口编号：IFC_TacDataPoolComb002
	 * <p>交易码：2005002
	 * <p>接口名称：指标组合查询[分页]
	 * 
	 * @return 股票列表
	 */
	public T02005002._rsp filtration(String weixinAccountId, FilterBody filter);
}
