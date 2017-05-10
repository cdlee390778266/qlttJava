package com.qianlong.webapp.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qianlong.webapp.domain.BaseIndex;
import com.qianlong.webapp.service.IMyAttentionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-minidao.xml", "classpath:spring-mvc-aop.xml",
		"classpath:spring-mvc-context.xml", "classpath:spring-mvc-hibernate.xml", "classpath:spring-mvc-timeTask.xml",
		"classpath:spring-mvc.xml" })
public class MyAttentionServiceImplTest {

	private Logger logger = Logger.getLogger(MyAttentionServiceImplTest.class);
	
	@Autowired
	private IMyAttentionService myAttentionService;
	
	//@Test
	public void testFollow() {
		BaseIndex index = new BaseIndex();
		index.setTacTic("100000000");
		index.setTacPrm(0);
		myAttentionService.follow(index, "tt1493095617721");
	}
	
	//@Test
	public void testUnfollow() {
		BaseIndex index = new BaseIndex();
		index.setTacTic("100000000");
		index.setTacPrm(0);
		myAttentionService.unfollow(index, "tt1493095617721");
	}
	
	@Test
	public void testFollowingList() {
		List<BaseIndex> list = myAttentionService.followingList("tt1493095617721");
		logger.debug(list);
		assertTrue("testFollowingList测试未通过", !CollectionUtils.isEmpty(list));
	}

}
