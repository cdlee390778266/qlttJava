package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.service.ICombinedIndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-minidao.xml", "classpath:spring-mvc-aop.xml",
		"classpath:spring-mvc-context.xml", "classpath:spring-mvc-hibernate.xml", "classpath:spring-mvc-timeTask.xml",
		"classpath:spring-mvc.xml" })
public class CombinedIndexServiceImplTest {
	
	private Logger logger = Logger.getLogger(IndexSystemServiceImplTest.class);
	
	@Autowired
	private ICombinedIndexService combinedIndexService;

	@Test
	public void testAddComIndex() {
		logger.debug("testAddComIndex");
		List<SourceIndex> indices = new ArrayList<>();
		indices.add(new SourceIndex("100000000", 0));
		indices.add(new SourceIndex("100002000", 0));
		String tactic = combinedIndexService.addComIndex(indices);
		assertTrue("testAddComIndex", tactic != null);
	}

}
