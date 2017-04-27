package com.qianlong.qltt.us.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac001Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac002Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac003Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Rsp;
import com.qianlong.qltt.us.service.IAcctAttnTacService;

@Service("acctAttnTacService")
public class AcctAttnTacServiceImpl extends CommServiceImpl implements IAcctAttnTacService {

	@Override
	@Transactional
	public CommRsp attntac001(AcctAttnTac001Req req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public CommRsp attntac002(AcctAttnTac002Req req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public CommRsp attntac003(AcctAttnTac003Req req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public AcctAttnTac004Rsp attntac004(AcctAttnTac004Req req) {
		// TODO Auto-generated method stub
		return null;
	}
}
