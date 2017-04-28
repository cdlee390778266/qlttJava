package com.qianlong.qltt.us.service;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushFreq;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushNum;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushScope;

public interface IAcctSetPrmService {

	Object setprm001(SetPrmPushScope req);

	SetPrmPushScope setprm002(Ttacct req);

	CommRsp setprm003(SetPrmPushNum req);

	SetPrmPushNum setprm004(Ttacct req);

	CommRsp setprm005(SetPrmPushFreq req);

	SetPrmPushFreq setprm006(Ttacct req);


}
