package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.util.ResourceUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
import com.qianlong.webapp.exception.HttpRequestException;
import com.qianlong.webapp.service.IUserServCoreService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-minidao.xml", "classpath:spring-mvc-aop.xml",
		"classpath:spring-mvc-context.xml", "classpath:spring-mvc-hibernate.xml", "classpath:spring-mvc-timeTask.xml",
		"classpath:spring-mvc.xml" })
public class UserServCoreServiceImplTest {

	private Logger logger = Logger.getLogger(UserServCoreServiceImplTest.class);

	@Autowired
	IUserServCoreService userServCoreService;

	@Test
	public void testObtainAccessToken() throws HttpRequestException {
		UserServAccessTokenReqBody body = new UserServAccessTokenReqBody();
		body.setAppid(ResourceUtil.getConfigByName("user.serv.appid"));
		body.setSecret(ResourceUtil.getConfigByName("user.serv.secret"));
		body.setPlaintext(ResourceUtil.getConfigByName("user.serv.plaintext"));
		UserServAccessToken token = userServCoreService.obtainAccessToken(body);
		logger.debug(token);
		assertTrue("testProduceAuthCode", token != null && !StringUtils.isEmpty(token.getAccessToken()));
	}

}
