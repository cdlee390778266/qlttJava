package com.qianlong.qltt.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUsCombTacMenu;
import com.qianlong.qltt.us.domain.TUsCombTacMenuExample;
import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.mapper.TUsCombTacMenuMapper;
import com.qianlong.qltt.us.protocol.PageRspParameter;
import com.qianlong.qltt.us.protocol.TtacctPageReq;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu001Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu002Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu003Req;
import com.qianlong.qltt.us.protocol.tacmenu.AcctTacMenu004Rsp;
import com.qianlong.qltt.us.protocol.tacmenu.AddTacMenu;
import com.qianlong.qltt.us.protocol.tacmenu.AddTacMenu003;
import com.qianlong.qltt.us.service.IAcctTacMenuService;

@Service("acctTacMenuService")
public class AcctTacMenuServiceImpl extends CommServiceImpl implements IAcctTacMenuService{
	
	@Autowired
	private TUsCombTacMenuMapper tUsCombTacMenuMapper;
	
	@Override
	@Transactional
	public CommRsp tacmenu001(AcctTacMenu001Req req) {
		String acct = req.getTtacct();
		AddTacMenu addTacMenu = req.getAddtacmenu();
		//向tuscombtacmenu表插入新记录。顺序号按这个账户下已经存在的最大顺序号递增
		Integer order = getTacMenuOrder(acct);
		TUsCombTacMenu tUsCombTacMenu = new TUsCombTacMenu();
		tUsCombTacMenu.setFsTtacct(acct);
		tUsCombTacMenu.setFsTactic(addTacMenu.getTactic());
		tUsCombTacMenu.setFsName(addTacMenu.getTacname());
		tUsCombTacMenu.setFsDetail(addTacMenu.getTacdetail());
		tUsCombTacMenu.setFiOrder(order);
		tUsCombTacMenuMapper.insert(tUsCombTacMenu);
		return new CommRsp();
	}

	private Integer getTacMenuOrder(String acct) {
		return tUsCombTacMenuMapper.selectMaxOrderByTtacct(acct)+1;
	}

	@Override
	@Transactional
	public CommRsp tacmenu002(AcctTacMenu002Req req) {
		TUsCombTacMenuExample example = new TUsCombTacMenuExample();
		TUsCombTacMenuExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		criteria.andFsTacticEqualTo(req.getDeltacmenu().getTactic());
		tUsCombTacMenuMapper.deleteByExample(example);
		return new CommRsp();
	}

	@Override
	@Transactional
	public CommRsp tacmenu003(AcctTacMenu003Req req) {
		//向tuscombtacmenu表删除该账户下的所有组合指标菜单
		TUsCombTacMenuExample example = new TUsCombTacMenuExample();
		TUsCombTacMenuExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		tUsCombTacMenuMapper.deleteByExample(example);
		List<AddTacMenu003> menu003s = req.getAddtacmenu();
		if(menu003s != null && !menu003s.isEmpty()){
			for(AddTacMenu003 menu003 : menu003s){
				TUsCombTacMenu tUsCombTacMenu = new TUsCombTacMenu();
				tUsCombTacMenu.setFsTtacct(req.getTtacct());
				tUsCombTacMenu.setFsTactic(menu003.getTactic());
				tUsCombTacMenu.setFsName(menu003.getTacname());
				tUsCombTacMenu.setFiOrder(menu003.getOrder());
				tUsCombTacMenu.setFsDetail(menu003.getTacdetail());
				tUsCombTacMenuMapper.insert(tUsCombTacMenu);//向tuscombtacmenu表插入新记录
			}
		}
		return new CommRsp();
	}

	@Override
	@Transactional(readOnly = true)
	public AcctTacMenu004Rsp tacmenu004(TtacctPageReq req) {
		TUsCombTacMenuExample example = new TUsCombTacMenuExample();
		TUsCombTacMenuExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		if(req.getPagereq() != null){
			example.setLimitStart(req.getPagereq().getReqstart());
			example.setLimitEnd(req.getPagereq().getReqnum());
		}
		example.setOrderByClause("`Fi_order` ASC");
		List<TUsCombTacMenu> tUsCombTacMenus = tUsCombTacMenuMapper.selectByExample(example);
		int num = tUsCombTacMenuMapper.countByExample(example);
		
		AcctTacMenu004Rsp rsp = new AcctTacMenu004Rsp();
		PageRspParameter pageRspParameter = new PageRspParameter();
		pageRspParameter.setRspnum(tUsCombTacMenus == null?0:tUsCombTacMenus.size());
		pageRspParameter.setTotalnum(num);
		rsp.setPagersp(pageRspParameter);
		
		List<AddTacMenu>  tacmenus = new ArrayList<AddTacMenu>();
		rsp.setTacmenu(tacmenus);
		
		if(tUsCombTacMenus !=null && ! tUsCombTacMenus.isEmpty()){
			AddTacMenu tacMenu = null;
			for(TUsCombTacMenu tUsCombTacMenu : tUsCombTacMenus){
				tacMenu = new AddTacMenu();
				tacMenu.setTacdetail(tUsCombTacMenu.getFsDetail());
				tacMenu.setTacname(tUsCombTacMenu.getFsName());
				tacMenu.setTactic(tUsCombTacMenu.getFsTtacct());
			}
		}
		return rsp;
	}
}
	

