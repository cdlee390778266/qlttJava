package com.qianlong.qlttms.service;

import com.qianlong.qlttms.domain.TacPoolReqBody;
import com.qlcd.qltt.body.pvt.T02001001;
import com.qlcd.qltt.body.pvt.T02001002;
import com.qlcd.qltt.body.pvt.T02001003;
import com.qlcd.qltt.body.pvt.T02001004;
import com.qlcd.qltt.body.pvt.T02003001;

public interface IIndexSystemService {

	/**
	 * <p>接口编号：IFC_TacProTacticDefine002
	 * <p>交易码：2001002
	 * <p>接口名称：原生指标组清单查询
	 * 
	 * @return 原生指标组清单
	 */
	public T02001002._rsp queryIdxGroup(String weixinAccountId);
	
	/**
	 * <p>接口编号：IFC_TacProTacticDefine003
	 * <p>交易码：2001003
	 * <p>接口名称：指定原生指标组成员清单查询
	 * 
	 * @param tacGroup 指标组
	 * 
	 * @return 指定原生指标组成员清单
	 */
	public T02001003._rsp queryIdxByGroup(String weixinAccountId, String tacGroup);
	
	/**
	 * <p>接口编号：IFC_TacProTacticDefine001
	 * <p>交易码：2001001
	 * <p>接口名称：原生指标清单查询
	 * 
	 * @return 原生指标清单
	 */
	public T02001001._rsp queryAllIdx(String weixinAccountId);
	
	/**
	 * <p>接口编号：IFC_TacProTacticDefine004
	 * <p>交易码：2003001
	 * <p>接口名称：最新实时行情指标数据池查询[分页]
	 * 
	 * @return 最新实时行情指标数据
	 */
	public T02001004._rsp queryIdxDefine(String weixinAccountId, String  tactic);
	
	/**
	 * <p>接口编号：IFC_TacDataPoolRealTime001
	 * <p>交易码：2003001
	 * <p>接口名称：最新实时行情指标数据池查询[分页]
	 * 
	 * @return 最新实时行情指标数据
	 */
	public T02003001._rsp queryTacDataPool(String weixinAccountId, TacPoolReqBody body);
	
	
}
