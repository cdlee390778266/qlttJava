package com.qianlong.qltt.us.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianlong.qltt.us.domain.acct.TUSAcctBaseInfo;
import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.domain.device.TUSChnlDevInfo;
import com.qianlong.qltt.us.mapper.acct.TUSAcctBaseInfoMapper;
import com.qianlong.qltt.us.mapper.device.TUSChnlDevInfoMapper;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo001Req;
import com.qianlong.qltt.us.protocol.acctinfo.AcctInfo002Req;
import com.qianlong.qltt.us.service.IAcctInfoService;

@Service("acctInfoService")
public class AcctInfoServiceImpl extends CommServiceImpl implements IAcctInfoService {

//	@Autowired
//	private TUSAcctCNRegMapper tUSAcctCNRegMapper;

	@Autowired
	private TUSAcctBaseInfoMapper tUSAcctBaseInfoMapper;

//	@Autowired
//	private TUSBindRelMapper tUSBindRelMapper;

	@Autowired
	private TUSChnlDevInfoMapper tUSChnlDevInfoMapper;

	@Override
	public CommRsp info001(AcctInfo001Req req) {
		//向tusacctbaseinfo表更新指定ttacct的信息，ttacct本身禁止修改
		TUSAcctBaseInfo tusAcctBaseInfo = new TUSAcctBaseInfo();
		tusAcctBaseInfo.setFsTtacct(req.getTtacct());
		tusAcctBaseInfo.setFsName(req.getName());
		tUSAcctBaseInfoMapper.updateByPrimaryKey(tusAcctBaseInfo);
		return new CommRsp();
	}

	@Override
	public CommRsp info002(AcctInfo002Req req) {
		//向tuschnldevinfo表更新指定ttacct、Fi_svcchnl=2（手机App）的渠道设备信息，包括设备号和设备类型。
		TUSChnlDevInfo tusChnlDevInfo = new TUSChnlDevInfo();
		tusChnlDevInfo.setFsTtacct(req.getTtacct());
		tusChnlDevInfo.setFiSvcchnl(2);
		tusChnlDevInfo.setFiDevtype(req.getDevtype());
		tusChnlDevInfo.setFsDevno(req.getDevno());
		int num = tUSChnlDevInfoMapper.updateByPrimaryKeySelective(tusChnlDevInfo);
		//如果上述更新操作受影响记录数为0，则说明尚未建立账号的手机渠道信息，因此新建信息：
		//向tuschnldevinfo插入指定ttacct的账号渠道设备信息，Fi_svcchnl设置为2，Fi_switch默认设置为1.开启。
		if(num == 0){
			tusChnlDevInfo.setFiSwitch(1);
			tUSChnlDevInfoMapper.insert(tusChnlDevInfo);
		}
		return new CommRsp();
	}
}
