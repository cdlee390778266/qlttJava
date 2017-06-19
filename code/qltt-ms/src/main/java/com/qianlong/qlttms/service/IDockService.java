package com.qianlong.qlttms.service;

import com.qianlong.qlttms.domain.UserServAccessToken;

public interface IDockService {

	public <T> T request(String weixinAccountid, String trdCode, Object requestBody);
	
	
	public void loadDockConfig();
	
	
	public UserServAccessToken getCurrentAccessToken(String dockKey); 
	
	
	public void resetAccessTokenByAccountid(String weixinAccountid, long errorCode);
	

}
