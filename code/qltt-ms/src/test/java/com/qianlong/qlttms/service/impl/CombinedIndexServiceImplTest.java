package com.qianlong.qlttms.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.qianlong.qlttms.domain.SourceIndex;
import com.qianlong.qlttms.domain.SrcIndexReqBody;
import com.qianlong.qlttms.service.ICombinedIndexService;
import com.qlcd.qltt.body.pvt.T02002002;

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
		String tactic = combinedIndexService.addComIndex("", indices);
		assertTrue("testAddComIndex", tactic != null);
	}

	@Test
	public void testQueryComIndex() {
		SrcIndexReqBody body = new SrcIndexReqBody();
		body.setStart(1);
		body.setSize(5);
		T02002002._rsp rsp = combinedIndexService.queryComIndex("", body);
		List<T02002002._combtactic> combtacticList = rsp.getCbtlistList();
		if (!CollectionUtils.isEmpty(combtacticList)) {
			for(T02002002._combtactic combtactic : combtacticList) {
				List<T02002002._combele> combeleList = combtactic.getCbelistList();
				logger.debug(String.format("组合指标编号: [%s]", combtactic.getTactic()));
				if (!CollectionUtils.isEmpty(combeleList)) {
					for (T02002002._combele combele : combeleList) {
						logger.debug(String.format("组合指标[%s]包含的原生指标: {原生指标编号: %s, 原生指标参数: %d}", combtactic.getTactic(), combele.getSrctactic(), combele.getSrctacticprm()));
					}
				}
			}
		}
		assertTrue("testAddComIndex", !CollectionUtils.isEmpty(combtacticList));
	}
}
