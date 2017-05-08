package com.qianlong.qltt.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(AcctAttnTacServiceImpl.class);
	
	@Autowired
	private TUsAttnTacTicMapper tUsAttnTacTicMapper;

	@Override
	@Transactional
	public CommRsp attntac001(AcctAttnTac001Req req) {
		TUsAttnTacTic tUsAttnTacTic =  createTUsAttnTacTics(req.getTtacct(),req.getAttntactic());
		tUsAttnTacTicMapper.insert(tUsAttnTacTic);
		return new CommRsp();
	}

	private TUsAttnTacTic  createTUsAttnTacTics(String ttacct, AttnTacTic attntactic){
		TUsAttnTacTic tUsAttnTacTic = new TUsAttnTacTic();
		tUsAttnTacTic.setFsTtacct(ttacct);
		tUsAttnTacTic.setFsTactic(attntactic.getTactic());
		tUsAttnTacTic.setFiTacticprm(attntactic.getTacprm());
		return tUsAttnTacTic;
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
		TUsAttnTacTic usAttnTacTic =  createTUsAttnTacTics(req.getTtacct(),req.getCncltactic());
		tUsAttnTacTicMapper.deleteByPrimaryKey(usAttnTacTic);
		return new CommRsp();
	}

	
	@Override
	@Transactional
	public CommRsp attntac003(AcctAttnTac003Req req) {
		//先删除
		TUsAttnTacTicExample example = new TUsAttnTacTicExample();
		TUsAttnTacTicExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		int num = tUsAttnTacTicMapper.deleteByExample(example);
		logger.debug("删除了"+num+"条数据");
		//后插入
		List<TUsAttnTacTic> usAttnTacTics = createTUsAttnTacTics(req.getTtacct(), req.getAttntactic());
		if(usAttnTacTics !=null && !usAttnTacTics.isEmpty()){
			num= tUsAttnTacTicMapper.batchInsert(usAttnTacTics);
		}
		logger.debug("新增了"+num+"条数据");
		return new CommRsp();
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public AcctAttnTac004Rsp attntac004(AcctAttnTac004Req req) {
		AcctAttnTac004Rsp rsp = new AcctAttnTac004Rsp();
		List<AttnTacTic> attnTacTics = tUsAttnTacTicMapper.selectByTTAcct(req.getTtacct());
		//拼装返回数据
		rsp.setAttntactic(attnTacTics);
		return rsp;
	}
}
