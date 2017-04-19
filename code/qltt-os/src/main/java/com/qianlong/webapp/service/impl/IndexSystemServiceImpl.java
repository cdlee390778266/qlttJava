package com.qianlong.webapp.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.webapp.service.IIndexSystemService;
import com.qlcd.qltt.body.prt.T02001002;
import com.qlcd.util.ZMQProxyClient;

@Service
public class IndexSystemServiceImpl implements IIndexSystemService {
	
	private Logger logger = Logger.getLogger(IndexSystemServiceImpl.class);
	
	@Autowired
	private ZMQProxyClient zmqProxyClient;

	@Override
	public Object queryIdxGroup() {
		logger.debug("原生指标组清单查询");
		T02001002._protacgroup.Builder builder = T02001002._protacgroup.newBuilder();
		Object obj = zmqProxyClient.OutBound("2001002", builder.build());
		return obj;
	}

}
