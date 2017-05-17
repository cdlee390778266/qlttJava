package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.domain.PushFreqEntity;
import com.qianlong.webapp.domain.PushScopeEntity;
import com.qianlong.webapp.service.ISettingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-minidao.xml", "classpath:spring-mvc-aop.xml",
		"classpath:spring-mvc-context.xml", "classpath:spring-mvc-hibernate.xml", "classpath:spring-mvc-timeTask.xml",
		"classpath:spring-mvc.xml" })
public class SettingServiceImplTest {
	
	private Logger logger = Logger.getLogger(SettingServiceImplTest.class);

	@Autowired
	private ISettingService settingService;
	
	@Test
	public void testSetPushScope() {
		logger.debug("测试 - 全局推送范围设置");
		settingService.setPushScope("10000", 0, 0);
		PushScopeEntity pushScope = settingService.getPushScope("10000");
		assertTrue("testSetPushScope测试未通过", pushScope != null && pushScope.getPushScope() == 0);
	}

	@Test
	public void testSetPushFreq() {
		logger.debug("测试 - 全局推送频率设置");
		settingService.setPushFreq("10000", 1000900);
		PushFreqEntity pushFreq = settingService.getPushFreq("10000");
		assertTrue("testSetPushScope测试未通过", pushFreq != null && pushFreq.getPushFreq() == 1000900);
	}
}
