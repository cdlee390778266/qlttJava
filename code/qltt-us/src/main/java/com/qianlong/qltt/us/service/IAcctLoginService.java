package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin001Req;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin001Rsp;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin002Req;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin002Rsp;

public interface IAcctLoginService {

	AcctLogin001Rsp login001(AcctLogin001Req req);

	AcctLogin002Rsp login002(AcctLogin002Req req);

}
