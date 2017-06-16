package com.qianlong.qlttms.service;

import com.qianlong.qlttms.domain.AuthResultEntity;
import com.qianlong.qlttms.domain.RegisterEntity;
import com.qianlong.qlttms.domain.TrenchInfoEnity;
import com.qianlong.qlttms.exception.HttpRequestException;

public interface IUserServCoreService {

	
	/**
	 * 账号登录(第三方验证授权登录)
	 * @param user
	 * @return
	 */
	public AuthResultEntity tdPartAuthLogin(String weixinAccountId,TrenchInfoEnity user) throws HttpRequestException;
	
	/**
	 * 账号开通(手机号识别)
	 * @param regInfo
	 * @return
	 * @throws HttpRequestException
	 */
	public AuthResultEntity userAcctOpen(String weixinAccountId,RegisterEntity regInfo) throws HttpRequestException;


}
