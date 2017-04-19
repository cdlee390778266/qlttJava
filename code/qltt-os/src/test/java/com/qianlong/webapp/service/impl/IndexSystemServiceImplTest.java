package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.pvt.T02001002;

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
		T02001002._rsp rsp = indexSystemService.queryIdxGroup();
		logger.debug(String.format("结果类: [%s]", rsp != null ? rsp.getClass().getName() : null));
		int ptgcnt = rsp.getPtgcnt();   //指标组数量
		logger.debug(String.format("指标组数量:[%d]", ptgcnt));
		List<T02001002._protacgroup> list = rsp.getPtglistList();   //指标组清单
		if (!CollectionUtils.isEmpty(list)) {
			for (T02001002._protacgroup group : list) {
				logger.debug(String.format("指标组:[%s], 指标组名称:[%s], 指标组描述:[%s]", group.getTacgroup(), group.getGrpname(), group.getGrpdetail()));
			}
		}
		
		assertTrue("testQueryIdxGroup", rsp != null);
	}

}
