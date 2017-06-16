package com.qianlong.qlttms.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.qlttms.domain.SourceIndex;
import com.qianlong.qlttms.domain.UserAcctTac;
import com.qianlong.qlttms.service.ICollectService;
import com.qianlong.qlttms.service.ICombinedIndexService;
import com.qianlong.qlttms.service.IUserAcctTacMenuService;

@Service
public class CollectServiceImpl implements ICollectService {
	
	private Logger logger = Logger.getLogger(CollectServiceImpl.class);
	
	@Autowired
	private ICombinedIndexService combinedIndexService;
	
	@Autowired
	private IUserAcctTacMenuService userAcctTacMenuService;

	@Override
	public void collect(String weixinAccountId,List<SourceIndex> indices, UserAcctTac addTacMenu, String ttacct) {
		logger.debug("收藏组合指标");
		String tactic = combinedIndexService.addComIndex(weixinAccountId, indices);
		addTacMenu.setTacTic(tactic);
		userAcctTacMenuService.add(weixinAccountId, addTacMenu, ttacct);
	}
}
