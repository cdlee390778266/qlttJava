package com.qianlong.webapp.service;

import com.qianlong.webapp.domain.PushFreqEntity;
import com.qianlong.webapp.domain.PushNumEntity;
import com.qianlong.webapp.domain.PushScopeEntity;

/**
 * 
 * 参数设置
 * @author wangk
 * 
 */
public interface ISettingService {

	/**
	 * 全局推送范围设置
	 * 
	 * @param ttacct 推推账号
	 * @param pushScope 推送范围
	 * @param scopePrm 范围参数
	 */
	public void setPushScope(String ttacct, Integer pushScope, Integer scopePrm);
	
	/**
	 * 全局推送数量设置
	 * 
	 * @param ttacct 推推账号
	 * @param pushNum 推送数量
	 */
	public void setPushNum(String ttacct, Integer pushNum);
	
	/**
	 * 全局推送频率设置
	 * 
	 * @param ttacct 推推账号
	 * @param pushFreq 推送频率
	 */
	public void setPushFreq(String ttacct, Integer pushFreq);
	
	/**
	 * 全局推送范围查询
	 * 
	 * @param ttacct 推推账号
	 * @return 全局推送范围
	 */
	public PushScopeEntity getPushScope(String ttacct);
	
	/**
	 * 全局推送范围查询
	 * 
	 * @param ttacct 推推账号
	 * @return 全局推送数量
	 */
	public PushNumEntity getPushNum(String ttacct);
	
	/**
	 * 全局推送频率查询
	 * 
	 * @param ttacct 推推账号
	 * @return 全局推送频率
	 */
	public PushFreqEntity getPushFreq(String ttacct);
}
