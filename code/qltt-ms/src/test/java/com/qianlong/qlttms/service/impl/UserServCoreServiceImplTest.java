package com.qianlong.qlttms.service.impl;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.qlttms.domain.UserServAccessToken;
import com.qianlong.qlttms.domain.UserServAccessTokenReqBody;
import com.qianlong.qlttms.exception.HttpRequestException;
import com.qianlong.qlttms.service.IUserServCoreService;
import com.qianlong.qlttms.utils.ResourceUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mvc-hibernate.xml", 
		"classpath:spring-mvc.xml" })
public class UserServCoreServiceImplTest {

	private Logger logger = Logger.getLogger(UserServCoreServiceImplTest.class);

	@Autowired
	IUserServCoreService userServCoreService;

	@Test
	public void testObtainAccessToken() throws HttpRequestException {
		
	}

}
