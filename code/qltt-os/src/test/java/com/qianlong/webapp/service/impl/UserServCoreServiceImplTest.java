package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.domain.UserServAccessToken;
import com.qianlong.webapp.domain.UserServAccessTokenReqBody;
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
	public void testObtainAccessToken() {
		UserServAccessTokenReqBody body = new UserServAccessTokenReqBody();
		body.setAppid("wx_000001");
		body.setSecret("OuIEqeoVdaKG9K4vrUqBd2UsFJouRamdSZdnEKFh3dOuL0t1zzbaI8VF/ZKSbyFRckd/vsihXVfFqSOC8anZANVlCcDQ1uHi2AEdQdsN/UYl/09XCJVRPl2M3295g/l3kdGgnWxWJc5iAr4HYNu7DEKw53k4bbXWLzPReU42YcE=");
		body.setPlaintext("AhQ");
		UserServAccessToken token = userServCoreService.obtainAccessToken(body);
		logger.debug(token);
		assertTrue("testProduceAuthCode", token != null && !StringUtils.isEmpty(token.getAccessToken()));
	}

}
