package com.qianlong.qlttms.service;

import javax.servlet.http.HttpSession;

import com.qianlong.qlttms.domain.AuthResultEntity;

/**
 * 乾隆推推用户管理
 * @author wangk
 *
 */
public interface ISubscriberService {
	
	/**
	 * 微信用户登录
	 * @param req
	 * @return
	 */
	public AuthResultEntity wechatUserLogin(String code, String state, HttpSession session);
}
