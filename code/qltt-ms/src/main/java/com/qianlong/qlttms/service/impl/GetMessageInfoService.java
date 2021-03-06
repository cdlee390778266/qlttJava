package com.qianlong.qlttms.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.qlttms.service.IDockService;
import com.qianlong.qlttms.service.IGetMessageInfoService;
import com.qlcd.qltt.body.pvt.T02004001;
import com.qlcd.qltt.body.pvt.T02004002;

@Service
public class GetMessageInfoService implements IGetMessageInfoService {
	
	private Logger logger = Logger.getLogger(GetMessageInfoService.class);
	
	@Autowired
	private IDockService dockService;

	@Override
	public String queryT3001001(String weixinAccountId, String eventdate, Integer eventno, String ttacct, Integer svcchnl) {
		logger.debug("即时推送记录查询,交易码：2004001 对应3001001协议推送记录");
		T02004001._req.Builder builder = T02004001._req.newBuilder();
		builder.setEventdate(eventdate);
		builder.setEventno(eventno);
		builder.setTtacct(ttacct);
		builder.setSvcchnl(svcchnl);
		T02004001._rsp rsp = dockService.request(weixinAccountId, "2004001", builder.build());
		String content = rsp.getContent();
		return content;
	}

	@Override
	public String queryT3001002(String weixinAccountId, String eventdate, Integer eventno, String ttacct,
			Integer svcchnl) {
		logger.debug("即时推送记录查询,交易码：2004002 对应3001002协议推送记录");
		T02004002._req.Builder builder = T02004002._req.newBuilder();
		builder.setEventdate(eventdate);
		builder.setEventno(eventno);
		builder.setTtacct(ttacct);
		builder.setSvcchnl(svcchnl);
		T02004002._rsp rsp =  dockService.request(weixinAccountId, "2004002", builder.build());
		String content = rsp.getContent();
		return content;
	}
}
