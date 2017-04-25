package com.qianlong.qltt.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUsAttnTacTic;
import com.qianlong.qltt.us.domain.TUsAttnTacTicExample;
import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.mapper.TUsAttnTacTicMapper;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac001Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac002Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac003Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Req;
import com.qianlong.qltt.us.protocol.acctattntac.AcctAttnTac004Rsp;
import com.qianlong.qltt.us.protocol.acctattntac.AttnTacTic;
import com.qianlong.qltt.us.service.IAcctAttnTacService;
import com.qianlong.qltt.us.util.StringUtil;

@Service("acctAttnTacService")
public class AcctAttnTacServiceImpl extends CommServiceImpl implements IAcctAttnTacService {
	
	@Autowired
	private TUsAttnTacTicMapper tUsAttnTacTicMapper;

	@Override
	@Transactional
	public CommRsp attntac001(AcctAttnTac001Req req) {
		List<TUsAttnTacTic> usAttnTacTics = createTUsAttnTacTics(req.getTtacct(),req.getAttntactic());
		if(usAttnTacTics !=null && !usAttnTacTics.isEmpty()){
			tUsAttnTacTicMapper.batchInsert(usAttnTacTics);
		}
		return new CommRsp();
	}

	
	private List<TUsAttnTacTic> createTUsAttnTacTics(String ttacct, List<AttnTacTic> attntactic) {
		if(StringUtil.isNullOrBlank(ttacct) ||
				attntactic == null || attntactic.isEmpty()){
			return null;
		}else{
			List<TUsAttnTacTic> usAttnTacTics = new ArrayList<TUsAttnTacTic>();
			TUsAttnTacTic tUsAttnTacTic = null;
			for (AttnTacTic it: attntactic) {
				tUsAttnTacTic = new TUsAttnTacTic();
				tUsAttnTacTic.setFsTtacct(ttacct);
				tUsAttnTacTic.setFsTactic(it.getTactic());
				tUsAttnTacTic.setFiTacticprm(it.getTacprm());
				usAttnTacTics.add(tUsAttnTacTic);
			}
			return  usAttnTacTics;
		}
	}

	@Override
	@Transactional
	public CommRsp attntac002(AcctAttnTac002Req req) {
		List<TUsAttnTacTic> usAttnTacTics = createTUsAttnTacTics(req.getTtacct(),req.getCncltactic());
		if(usAttnTacTics != null && !usAttnTacTics.isEmpty()){
			for(TUsAttnTacTic tacTic : usAttnTacTics){
				tUsAttnTacTicMapper.deleteByPrimaryKey(tacTic);
			}
		}
		return new CommRsp();
	}

	
	@Override
	@Transactional
	public CommRsp attntac003(AcctAttnTac003Req req) {
		//先删除
		List<TUsAttnTacTic> usAttnTacTics = createTUsAttnTacTics(req.getTtacct(),req.getCncltactic());
		if(usAttnTacTics != null && !usAttnTacTics.isEmpty()){
			for(TUsAttnTacTic tacTic : usAttnTacTics){
				tUsAttnTacTicMapper.deleteByPrimaryKey(tacTic);
			}
		}
		//后插入
		usAttnTacTics = createTUsAttnTacTics(req.getTtacct(), req.getAttntactic());
		if(usAttnTacTics !=null && !usAttnTacTics.isEmpty()){
			tUsAttnTacTicMapper.batchInsert(usAttnTacTics);
		}
		return new CommRsp();
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public AcctAttnTac004Rsp attntac004(AcctAttnTac004Req req) {
		TUsAttnTacTicExample example = new TUsAttnTacTicExample();
		TUsAttnTacTicExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		List<TUsAttnTacTic> tUsAttnTacTics = tUsAttnTacTicMapper.selectByExample(example);
		//拼装返回数据
		AcctAttnTac004Rsp rsp = new AcctAttnTac004Rsp();
		List<AttnTacTic> attntactic = new ArrayList<AttnTacTic>();
		rsp.setAttntactic(attntactic);
		if(tUsAttnTacTics != null && !tUsAttnTacTics.isEmpty()){
			AttnTacTic tacTic = null;
			for(TUsAttnTacTic it:tUsAttnTacTics){
				tacTic = new AttnTacTic();
				tacTic.setTacprm(it.getFiTacticprm());
				tacTic.setTactic(it.getFsTactic());
				attntactic.add(tacTic);
			}
		}
		return rsp;
	}
}
