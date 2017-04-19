package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.service.IIndexSystemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-minidao.xml", "classpath:spring-mvc-aop.xml",
		"classpath:spring-mvc-context.xml", "classpath:spring-mvc-hibernate.xml", "classpath:spring-mvc-timeTask.xml",
		"classpath:spring-mvc.xml" })
public class IndexSystemServiceImplTest {
	
	private Logger logger = Logger.getLogger(IndexSystemServiceImplTest.class);
	
	@Autowired
	private IIndexSystemService indexSystemService;

	@Test
	public void testQueryIdxGroup() {
		Object object = indexSystemService.queryIdxGroup();
		logger.debug(String.format("结果类: [%s]", object != null ? object.getClass().getName() : null));
		assertTrue("testQueryIdxGroup", object != null);
	}

}
