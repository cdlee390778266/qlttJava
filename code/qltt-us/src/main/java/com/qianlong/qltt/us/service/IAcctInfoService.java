package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo001Req;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo002Req;

public interface IAcctInfoService {

	CommRsp info001(AcctInfo001Req req);

	CommRsp info002(AcctInfo002Req req);
}
