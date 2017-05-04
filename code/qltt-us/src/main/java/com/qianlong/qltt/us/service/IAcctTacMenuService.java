package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.TtacctPageReq;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu001Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu002Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu003Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu004Rsp;

public interface IAcctTacMenuService {

	CommRsp tacmenu001(AcctTacMenu001Req req);

	CommRsp tacmenu002(AcctTacMenu002Req req);

	CommRsp tacmenu003(AcctTacMenu003Req req);

	AcctTacMenu004Rsp tacmenu004(TtacctPageReq req);

}
