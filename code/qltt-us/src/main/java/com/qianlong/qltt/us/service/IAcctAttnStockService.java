package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Rsp;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStockCommReq;

public interface IAcctAttnStockService {

	CommRsp attnstock001(AcctAttnStockCommReq req);

	CommRsp attnstock002(AcctAttnStockCommReq req);

	CommRsp attnstock003(AcctAttnStockCommReq req);

	AcctAttnStock004Rsp attntac004(AcctAttnStock004Req req);

}
