package com.qianlong.webapp.service;

import javax.servlet.http.HttpSession;

import com.qianlong.webapp.domain.AuthResultEntity;

/**
 * 乾隆推推用户管理
 * @author wangk
 *
 */
public interface ISubscriberService {

	public void save();
	
	/**
	 * 微信用户登录
	 * @param req
	 * @return
	 */
	public AuthResultEntity wechatUserLogin(String code, String state, HttpSession session);
}
