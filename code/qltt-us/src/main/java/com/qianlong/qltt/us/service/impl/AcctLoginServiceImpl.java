package com.qianlong.qltt.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUSAcctCNReg;
import com.qianlong.qltt.us.domain.TUSAcctCNRegExample;
import com.qianlong.qltt.us.domain.TUSAcctCNRegExample.Criteria;
import com.qianlong.qltt.us.domain.TUSAcctCNRegKey;
import com.qianlong.qltt.us.domain.TUSBindRel;
import com.qianlong.qltt.us.domain.TUSBindRelExample;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.mapper.TUSAcctCNRegMapper;
import com.qianlong.qltt.us.mapper.TUSBindRelMapper;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin001Req;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin001Rsp;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin002Req;
import com.qianlong.qltt.us.protocol.acctlogin.AcctLogin002Rsp;
import com.qianlong.qltt.us.service.IAcctLoginService;

@Service("acctLoginService")
public class AcctLoginServiceImpl extends CommServiceImpl implements IAcctLoginService {

	@Autowired
	private TUSAcctCNRegMapper tUSAcctCNRegMapper;

	// @Autowired
	// private TUSAcctBaseInfoMapper tUSAcctBaseInfoMapper;

	@Autowired
	private TUSBindRelMapper tUSBindRelMapper;

	// @Autowired
	// private TUSChnlDevInfoMapper tUSChnlDevInfoMapper;

	@Override
	@Transactional(readOnly = true)
	public AcctLogin001Rsp login001(AcctLogin001Req req) {
		String cn = req.getCn();
		// 向tusacctcnreg表查询是否存在指定手机号且status=1的记录，如果存在，则拒绝开通新账户
		TUSAcctCNRegExample tusAcctCNRegExample = new TUSAcctCNRegExample();
		Criteria acreCriteria = tusAcctCNRegExample.createCriteria();
		acreCriteria.andFsCnEqualTo(cn);// 指定手机号
		acreCriteria.andFiStatusEqualTo(0);// 指定状态0
		List<TUSAcctCNReg> regList = tUSAcctCNRegMapper.selectByExample(tusAcctCNRegExample);
		if (regList != null && !regList.isEmpty()) {
			TUSAcctCNReg reg = regList.get(0);
			AcctLogin001Rsp rsp = new AcctLogin001Rsp();
			rsp.setCn(cn);
			rsp.setTtacct(reg.getFsTtacct());
			return rsp;
		} else {
			throw new QlttUSBusinessException(ErrorCodeMaster.ACCT_NOT_CORRECT);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public AcctLogin002Rsp login002(AcctLogin002Req req) {
		// 根据请求信息向tusbindrel表查询ttacct
		TUSBindRelExample tusBindRelExample = new TUSBindRelExample();
		com.qianlong.qltt.us.domain.TUSBindRelExample.Criteria brCriteria = tusBindRelExample.createCriteria();
		brCriteria.andFiSvcchnlEqualTo(req.getSvcchnl());
		brCriteria.andFsBindacctEqualTo(req.getBindacct());
		List<TUSBindRel> tusBindRels = tUSBindRelMapper.selectByExample(tusBindRelExample);
		if (tusBindRels != null && !tusBindRels.isEmpty()) {
			// 向tusacctcnreg表验证ttacct的状态必须为0
			String acct = tusBindRels.get(0).getFsTtacct();
			TUSAcctCNRegKey tusAcctCNRegKey = new TUSAcctCNRegKey();
			tusAcctCNRegKey.setFsTtacct(acct);
			TUSAcctCNReg tusAcctCNReg = tUSAcctCNRegMapper.selectByPrimaryKey(tusAcctCNRegKey);
			if (tusAcctCNReg.getFiStatus().equals(0)) {
				AcctLogin002Rsp rsp = new AcctLogin002Rsp();
				rsp.setCn(tusAcctCNReg.getFsCn());
				rsp.setTtacct(acct);
				return rsp;
			} else {
				throw new QlttUSBusinessException(ErrorCodeMaster.ACCT_NOT_CORRECT);
			}
		} else {
			throw new QlttUSBusinessException(ErrorCodeMaster.ACCT_NOT_CORRECT);
		}
	}
}
