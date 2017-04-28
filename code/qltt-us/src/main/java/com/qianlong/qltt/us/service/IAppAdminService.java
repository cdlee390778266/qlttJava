package com.qianlong.qltt.us.service;


import com.qianlong.qltt.us.domain.TUSSysApp;
import com.qianlong.qltt.us.protocol.app.AppCreate001Rsp;
import com.qianlong.qltt.us.protocol.app.TokenReset001;

public interface IAppAdminService {
	
	String generateAppid();

	AppCreate001Rsp createApp();

	TokenReset001 resetAppToken(TokenReset001 req);
	
	TUSSysApp validAppSecret(String appid,String plaintext, String secret);

}
