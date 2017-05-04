package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock001Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock002Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Rsp;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock003Req;

public interface IAcctAttnStockService {

	CommRsp attnstock001(AcctAttnStock001Req req);

	CommRsp attnstock002(AcctAttnStock002Req req);

	CommRsp attnstock003(AcctAttnStock003Req req);

	AcctAttnStock004Rsp attntac004(AcctAttnStock004Req req);

}
