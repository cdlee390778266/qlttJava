package com.qianlong.webapp.job;

import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IUserServCoreService;

@Service("userServTokenTask")
public class UserServTokenTask {

	private Logger logger = Logger.getLogger(UserServTokenTask.class);
	
	@Autowired
	private IUserServCoreService userServCoreService;
	
	public void autoResetToken() {
		logger.debug("自动重置用户服务器token");
		UserServAccessTokenReqBody body = new UserServAccessTokenReqBody();
		body.setAppid(ResourceUtil.getConfigByName("user.serv.appid"));
		body.setPlaintext(ResourceUtil.getConfigByName("user.serv.plaintext"));
		body.setSecret(ResourceUtil.getConfigByName("user.serv.secret"));
		try {
			userServCoreService.obtainAccessToken(body);
		} catch (HttpRequestException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
