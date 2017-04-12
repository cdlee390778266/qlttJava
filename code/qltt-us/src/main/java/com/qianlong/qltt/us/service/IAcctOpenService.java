package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Req;
import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Rsp;

/**
 * 渠道开户管理Service
 */
public interface IAcctOpenService {
	
	AcctOpen001Rsp open001(AcctOpen001Req req);
	
}
