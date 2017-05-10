package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac001Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac002Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac003Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Rsp;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac005Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac005Rsp;

public interface IAcctAttnTacService {
	
	CommRsp attntac001(AcctAttnTac001Req req);

	CommRsp attntac002(AcctAttnTac002Req req);
	
	CommRsp attntac003(AcctAttnTac003Req req);

	AcctAttnTac004Rsp attntac004(AcctAttnTac004Req req);

	AcctAttnTac005Rsp attntac005(AcctAttnTac005Req req);

}
