package com.qianlong.qltt.us.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUsGSetPushFreq;
import com.qianlong.qltt.us.domain.TUsGSetPushFreqKey;
import com.qianlong.qltt.us.domain.TUsGSetPushNum;
import com.qianlong.qltt.us.domain.TUsGSetPushNumKey;
import com.qianlong.qltt.us.domain.TUsGSetPushScope;
import com.qianlong.qltt.us.domain.TUsGSetPushScopeKey;
import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.mapper.TUsGSetPushFreqMapper;
import com.qianlong.qltt.us.mapper.TUsGSetPushNumMapper;
import com.qianlong.qltt.us.mapper.TUsGSetPushScopeMapper;
import com.qianlong.qltt.us.protocol.Ttacct;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushFreq;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushNum;
import com.qianlong.qltt.us.protocol.setprm.SetPrmPushScope;
import com.qianlong.qltt.us.service.IAcctSetPrmService;


@Service("acctSetPrmService")
public class AcctSetPrmServiceImpl extends CommServiceImpl implements IAcctSetPrmService{
	
	@Autowired
	private  TUsGSetPushScopeMapper tUsGSetPushScopeMapper;
	
	@Autowired
	private TUsGSetPushNumMapper tUsGSetPushNumMapper;
	
	@Autowired
	private TUsGSetPushFreqMapper tUsGSetPushFreqMapper;
	
	@Override
	@Transactional
	public Object setprm001(SetPrmPushScope req) {
		TUsGSetPushScope scope = new TUsGSetPushScope();
		scope.setFsTtacct(req.getTtacct());
		scope.setFiScopeprm(req.getScopeprm());
		scope.setFiPushscope(req.getPushscope());
		tUsGSetPushScopeMapper.updateByPrimaryKey(scope);//存在，就更新
		return new CommRsp();
	}

	@Override
	@Transactional(readOnly=true)
	public SetPrmPushScope setprm002(Ttacct req) {
		//从数据库查询数据
		TUsGSetPushScopeKey key = new TUsGSetPushScopeKey();
		key.setFsTtacct(req.getTtacct());
		TUsGSetPushScope scope = tUsGSetPushScopeMapper.selectByPrimaryKey(key);
		//拼装返回参数
		SetPrmPushScope rsp = new SetPrmPushScope();
		rsp.setTtacct(req.getTtacct());
		if(scope != null){
			rsp.setPushscope(scope.getFiPushscope());
			rsp.setScopeprm(scope.getFiScopeprm());
		}
		return rsp;
	}

	@Override
	@Transactional
	public CommRsp setprm003(SetPrmPushNum req) {
		TUsGSetPushNum tUsGSetPushNum = new TUsGSetPushNum();
		tUsGSetPushNum.setFsTtacct(req.getTtacct());
		tUsGSetPushNum.setFiPushnum(req.getPushnum());
		tUsGSetPushNumMapper.updateByPrimaryKey(tUsGSetPushNum);
		return new CommRsp();
	}

	@Override
	@Transactional(readOnly=true)
	public SetPrmPushNum setprm004(Ttacct req) {
		TUsGSetPushNumKey key = new TUsGSetPushNumKey();
		key.setFsTtacct(req.getTtacct());
		TUsGSetPushNum pushNumFromDB = tUsGSetPushNumMapper.selectByPrimaryKey(key);
		SetPrmPushNum setPrmPushNum = new SetPrmPushNum();
		setPrmPushNum.setTtacct(req.getTtacct());
		if(pushNumFromDB != null){
			setPrmPushNum.setPushnum(pushNumFromDB.getFiPushnum());
		}
		return setPrmPushNum;
	}

	@Override
	@Transactional
	public CommRsp setprm005(SetPrmPushFreq req) {
		TUsGSetPushFreq pushFreq = new TUsGSetPushFreq();
		pushFreq.setFsTtacct(req.getTtacct());
		pushFreq.setFiPushfreq(req.getPushfreq());	
		tUsGSetPushFreqMapper.updateByPrimaryKey(pushFreq);
		return new CommRsp();
	}

	@Override
	@Transactional(readOnly=true)
	public SetPrmPushFreq setprm006(Ttacct req) {
		TUsGSetPushFreqKey key = new TUsGSetPushFreqKey();
		key.setFsTtacct(req.getTtacct());
		TUsGSetPushFreq pushFreq = tUsGSetPushFreqMapper.selectByPrimaryKey(key);
		SetPrmPushFreq setPrmPushFreq = new SetPrmPushFreq();
		setPrmPushFreq.setTtacct(req.getTtacct());
		if(pushFreq != null){
			setPrmPushFreq.setPushfreq(pushFreq.getFiPushfreq());
		}
		return setPrmPushFreq;
	}
}
