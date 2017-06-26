package com.qianlong.qlttms.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.qianlong.qlttms.controller.HotTopicController;
import com.qianlong.qlttms.domain.BlockPage;
import com.qianlong.qlttms.service.IBlockService;
import com.qianlong.qlttms.service.IDockService;
import com.qlcd.qltt.body.BppBiz;
import com.qlcd.qltt.body.pvt.T02007002;
import com.qlcd.qltt.body.pvt.T02007004;

@Service
public class BlockServiceImpl implements IBlockService {
	
	
	private Logger logger = Logger.getLogger(BlockServiceImpl.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public com.qlcd.qltt.body.pvt.T02007002._rsp queryStockLabel(String weixinAccountid, String label,
			BlockPage page) {
		T02007002._req.Builder builder = T02007002._req.newBuilder();
		
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(page.getStart());
		pageBuilder.setReqnum(page.getSize());
		
		builder.setPgreq(pageBuilder.build());
		builder.setLabel(label);	
		
		
		String json;
		try {
			json = JsonFormat.printer().includingDefaultValueFields().print(builder.build());
			logger.debug(
					String.format("2007002交易 - 请求报文转换后的JSON字符串: [%s]", json));
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		T02007002._rsp rsp = dockService.request(weixinAccountid, "2007002", builder.build());
		return rsp;
	}
	
	

	@Override
	public com.qlcd.qltt.body.pvt.T02007004._rsp queryStockLimitLabel(
			String weixinAccountid, String[] labels, BlockPage page) {
		
		T02007004._req.Builder builder = T02007004._req.newBuilder();
		
		BppBiz._page_req.Builder pageBuilder = BppBiz._page_req.newBuilder();
		pageBuilder.setReqstart(page.getStart());
		pageBuilder.setReqnum(page.getSize());		
		builder.setPgreq(pageBuilder.build());
			
		for(String label: labels){
			builder.addLbstlist(T02007004._labelset.newBuilder().setLabel(label));
		}
		
		String json;
		try {
			json = JsonFormat.printer().includingDefaultValueFields().print(builder.build());
			logger.debug(
					String.format("2007004交易 - 请求报文转换后的JSON字符串: [%s]", json));
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		T02007004._rsp rsp = dockService.request(weixinAccountid, "2007004", builder.build());
		return rsp;
	}

}
