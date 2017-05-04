package com.qianlong.qltt.us.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUSAcctBaseInfo;
import com.qianlong.qltt.us.domain.TUSAcctCNReg;
import com.qianlong.qltt.us.domain.TUSAcctCNRegExample;
import com.qianlong.qltt.us.domain.TUSBindRel;
import com.qianlong.qltt.us.domain.TUSChnlDevInfo;
import com.qianlong.qltt.us.exception.ErrorCodeMaster;
import com.qianlong.qltt.us.exception.QlttUSBusinessException;
import com.qianlong.qltt.us.mapper.TUSAcctBaseInfoMapper;
import com.qianlong.qltt.us.mapper.TUSAcctCNRegMapper;
import com.qianlong.qltt.us.mapper.TUSBindRelMapper;
import com.qianlong.qltt.us.mapper.TUSChnlDevInfoMapper;
import com.qianlong.qltt.us.protocol.acctopen.AcctBindInfo;
import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Req;
import com.qianlong.qltt.us.protocol.acctopen.AcctOpen001Rsp;
import com.qianlong.qltt.us.service.IAcctOpenService;

@Service("acctOpenService")
public class AcctOpenServiceImpl extends CommServiceImpl implements IAcctOpenService {

	@Autowired
	private TUSAcctCNRegMapper tUSAcctCNRegMapper;

	@Autowired
	private TUSAcctBaseInfoMapper tUSAcctBaseInfoMapper;

	@Autowired
	private TUSBindRelMapper tUSBindRelMapper;

	@Autowired
	private TUSChnlDevInfoMapper tUSChnlDevInfoMapper;

	@Override
	@Transactional
	public AcctOpen001Rsp open001(AcctOpen001Req req) {
		String cn = req.getCn();
		Timestamp now = getSystemDate();

		// 向tusacctcnreg表查询是否存在指定手机号且status=1的记录，如果存在，则拒绝开通新账户
		TUSAcctCNRegExample tusAcctCNRegExample = new TUSAcctCNRegExample();
		TUSAcctCNRegExample.Criteria acreCriteria = tusAcctCNRegExample.createCriteria();
		acreCriteria.andFsCnEqualTo(cn);// 指定手机号
		acreCriteria.andFiStatusEqualTo(1);// 指定状态==1
		List<TUSAcctCNReg> regList = tUSAcctCNRegMapper.selectByExample(tusAcctCNRegExample);
		if (regList != null && !regList.isEmpty()) {
			throw new QlttUSBusinessException(ErrorCodeMaster.CN_IS_EXIST);
		}

		// 向tusacctcnreg插入新记录：根据规则生成推推账号，status=1
		String acctno = generateAcct();
		TUSAcctCNReg tUSAcctCNReg = new TUSAcctCNReg();
		tUSAcctCNReg.setFiStatus(0);
		tUSAcctCNReg.setFsCn(cn);
		tUSAcctCNReg.setFsTtacct(acctno);
		tUSAcctCNReg.setFtRegtime(now);
		tUSAcctCNReg.setFtUpdtime(now);
		tUSAcctCNRegMapper.insert(tUSAcctCNReg);

		// 向tusacctbaseinfo插入新记录，名称可以为空
		TUSAcctBaseInfo tusAcctBaseInfo = new TUSAcctBaseInfo();
		tusAcctBaseInfo.setFsTtacct(acctno);
		tUSAcctBaseInfoMapper.insert(tusAcctBaseInfo);

		// 如果存在<acctbindinfo>段
		AcctBindInfo acctBindInfo = req.getAcctbindinfo();
		if (acctBindInfo != null) {
			// 如果存在<acctbindinfo>段，则向tusbindrel插入新记录
			TUSBindRel tusBindRel = new TUSBindRel();
			tusBindRel.setFsTtacct(acctno);
			tusBindRel.setFiSvcchnl(acctBindInfo.getSvcchnl());
			tusBindRel.setFsBindacct(acctBindInfo.getBindacct());
			tUSBindRelMapper.insert(tusBindRel);

			// 如果存在<acctbindinfo>段，还需向tuschnldevinfo表插入新记录
			// 若服务渠道为1.微信，则将bindacct(openid)插入为Fs_devno，Fi_devtype设置为0，Fi_switch默认设置为1.开启
			TUSChnlDevInfo tusChnlDevInfo = new TUSChnlDevInfo();
			tusChnlDevInfo.setFsTtacct(acctno);
			tusChnlDevInfo.setFiSvcchnl(acctBindInfo.getSvcchnl());
			if (acctBindInfo.getSvcchnl().equals(1)) {
				tusChnlDevInfo.setFsDevno(acctBindInfo.getBindacct());
				tusChnlDevInfo.setFiDevtype(0);
				tusChnlDevInfo.setFiSwitch(1);
			}
			tUSChnlDevInfoMapper.insert(tusChnlDevInfo);
		}

		AcctOpen001Rsp rsp = new AcctOpen001Rsp();
		rsp.setCn(cn);
		rsp.setTtacct(acctno);
		return rsp;
	}

	private String generateAcct() {
		// TODO 按照一定的规则生成推推账号
		return "tt" + String.valueOf(getSystemDate().getTime());
	}
}
