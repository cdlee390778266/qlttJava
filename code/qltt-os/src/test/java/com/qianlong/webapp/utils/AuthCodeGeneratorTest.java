package com.qianlong.webapp.utils;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

public class AuthCodeGeneratorTest {
	
	private Logger logger = Logger.getLogger(AuthCodeGeneratorTest.class);

	@Test
	public void testProduceAuthCode() {
		String code = AuthCodeGenerator.produceAuthCode(6);
		logger.debug(String.format("Auth Code:[%s]", code));
		assertTrue("testProduceAuthCode", (code != null) && (code.length() == 6));
	}

}
