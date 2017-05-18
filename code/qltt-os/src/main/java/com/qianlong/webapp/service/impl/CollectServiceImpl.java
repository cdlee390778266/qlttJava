package com.qianlong.webapp.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.webapp.domain.SourceIndex;
import com.qianlong.webapp.domain.UserAcctTac;
import com.qianlong.webapp.service.ICollectService;
import com.qianlong.webapp.service.ICombinedIndexService;
import com.qianlong.webapp.service.IUserAcctTacMenuService;

@Service
public class CollectServiceImpl implements ICollectService {
	
	private Logger logger = Logger.getLogger(CollectServiceImpl.class);
	
	@Autowired
	private ICombinedIndexService combinedIndexService;
	
	@Autowired
	private IUserAcctTacMenuService userAcctTacMenuService;

	@Override
	public void collect(List<SourceIndex> indices, UserAcctTac addTacMenu, String ttacct) {
		logger.debug("收藏组合指标");
		String tactic = combinedIndexService.addComIndex(indices);
		addTacMenu.setTacTic(tactic);
		userAcctTacMenuService.add(addTacMenu, ttacct);
	}
	
	@Override
	public void follow(List<SourceIndex> indices, String ttacct) {
		//
	}

}
