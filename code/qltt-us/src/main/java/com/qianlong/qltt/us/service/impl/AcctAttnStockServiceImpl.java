package com.qianlong.qltt.us.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.us.domain.TUsAttnStock;
import com.qianlong.qltt.us.domain.TUsAttnStockExample;
import com.qianlong.qltt.us.domain.comm.CommRsp;
import com.qianlong.qltt.us.mapper.TUsAttnStockMapper;
import com.qianlong.qltt.us.protocol.PageRspParameter;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock001Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock002Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock003Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Req;
import com.qianlong.qltt.us.protocol.acctstock.AcctAttnStock004Rsp;
import com.qianlong.qltt.us.protocol.acctstock.AttnStock001;
import com.qianlong.qltt.us.protocol.acctstock.AttnStock003;
import com.qianlong.qltt.us.protocol.acctstock.AttnStockComm;
import com.qianlong.qltt.us.protocol.acctstock.StockPool;
import com.qianlong.qltt.us.protocol.acctstock.StockPoolIndex;
import com.qianlong.qltt.us.service.IAcctAttnStockService;

@Service("acctAttnStockService")
public class AcctAttnStockServiceImpl extends CommServiceImpl implements IAcctAttnStockService {
	
	@Autowired
	private TUsAttnStockMapper tUsAttnStockMapper;

	@Override
	@Transactional
	public CommRsp attnstock001(AcctAttnStock001Req req){
		String  ttacct = req.getTtacct();
		AttnStock001 attnStock = req.getAttnstock();
		List<StockPoolIndex> stockpool = attnStock.getStockpool();
		if(stockpool != null && !stockpool.isEmpty()){
			List<TUsAttnStock> tUsAttnStocks = new ArrayList<TUsAttnStock>();
			TUsAttnStock tUsAttnStock = null;
			for(StockPoolIndex sp:stockpool){
				tUsAttnStock = new TUsAttnStock();
				tUsAttnStocks.add(tUsAttnStock);
				tUsAttnStock.setFsTtacct(ttacct);
				tUsAttnStock.setFiPoolindex(sp.getPoolindex());
				Integer stockorder = getStockOrder(ttacct,sp.getPoolindex());
				tUsAttnStock.setFiStockorder(stockorder);
				tUsAttnStock.setFsStockcode(attnStock.getStockcode());
				tUsAttnStock.setFsStockname(attnStock.getStockname());
			}
			if(!tUsAttnStocks.isEmpty()){
				tUsAttnStockMapper.batchInsert(tUsAttnStocks);
			}
		}
		return new CommRsp();
	}

	
	private Integer getStockOrder(String ttacct, Integer poolindex) {
		return  tUsAttnStockMapper.selectMaxOfSorckOrder(ttacct,poolindex)+1;
	}

	@Override
	@Transactional
	public CommRsp attnstock002(AcctAttnStock002Req req){
		String  ttacct = req.getTtacct();
		AttnStock001 attnStock = req.getCnclstock();
		List<StockPoolIndex> stockpool = attnStock.getStockpool();
		TUsAttnStockExample example = null;
		if(stockpool != null && !stockpool.isEmpty()){
			for(StockPoolIndex sp:stockpool){
				//先将需要删除的记录删除
				example = new TUsAttnStockExample();
				TUsAttnStockExample.Criteria criteria = example.createCriteria();
				criteria.andFsTtacctEqualTo(ttacct);
				criteria.andFiPoolindexEqualTo(sp.getPoolindex());
				criteria.andFsStockcodeEqualTo(attnStock.getStockcode());
				tUsAttnStockMapper.deleteByExample(example);
				
				//再将剩下的记录重新排序
//				example = new TUsAttnStockExample();
//				criteria = example.createCriteria();
//				criteria.andFsTtacctEqualTo(ttacct);
//				criteria.andFiPoolindexEqualTo(sp.getPoolindex());
//				example.setOrderByClause("`Fi_stockorder` ASC");
//				
//				List<TUsAttnStock> stocks = tUsAttnStockMapper.selectByExample(example);
//				if( stocks != null && !stocks.isEmpty()){
//					//将排序的记录先删除
//					tUsAttnStockMapper.deleteByExample(example);
//					
//					//将记录排序
//					for (int i = 0; i < stocks.size();) {
//						stocks.get(i).setFiStockorder(++i);
//					}
//					//将排序好的记录插回数据库
//					tUsAttnStockMapper.batchInsert(stocks);
//				}
			}
		}
		return new CommRsp();
	}

	@Override
	@Transactional
	public CommRsp attnstock003(AcctAttnStock003Req req) {
		String  ttacct = req.getTtacct();
		List<StockPool> stockpool = req.getStockpool();
		if(stockpool != null && !stockpool.isEmpty()){
			TUsAttnStockExample example = null;
			List<TUsAttnStock> tUsAttnStocks = new ArrayList<TUsAttnStock>();
			TUsAttnStock tUsAttnStock = null;
			for(StockPool sp:stockpool){
				int poolindex = sp.getPoolindex();//选股池
				//先从tusattnstock表删除该ttacct下指定poolindex中的所有个股
				example = new TUsAttnStockExample();
				TUsAttnStockExample.Criteria criteria = example.createCriteria();
				criteria.andFsTtacctEqualTo(ttacct);
				criteria.andFiPoolindexEqualTo(poolindex);
				tUsAttnStockMapper.deleteByExample(example);
				
				//拼装往数据库新增的记录
				List<AttnStock003> attnstock = sp.getAttnstock();
				if(attnstock != null && !attnstock.isEmpty()){
					for(AttnStock003 stock:attnstock){
						tUsAttnStock = new TUsAttnStock();
						tUsAttnStocks.add(tUsAttnStock);
						tUsAttnStock.setFsTtacct(ttacct);
						tUsAttnStock.setFiPoolindex(sp.getPoolindex());
						tUsAttnStock.setFiStockorder(stock.getStockorder());
						tUsAttnStock.setFsStockcode(stock.getStockcode());
						tUsAttnStock.setFsStockname(stock.getStockname());
					}
				}
			}
			if(!tUsAttnStocks.isEmpty()){
				tUsAttnStockMapper.batchInsert(tUsAttnStocks);
			}
		}
		return new CommRsp();
	}


	@Override
	@Transactional(readOnly = true)
	public AcctAttnStock004Rsp attntac004(AcctAttnStock004Req req) {
		TUsAttnStockExample example = new TUsAttnStockExample();
		TUsAttnStockExample.Criteria criteria = example.createCriteria();
		criteria.andFsTtacctEqualTo(req.getTtacct());
		criteria.andFiPoolindexEqualTo(req.getPoolindex());
		if(req.getPagereq() != null){
			example.setLimitStart(req.getPagereq().getReqstart());
			example.setLimitEnd(req.getPagereq().getReqnum());
		}
		example.setOrderByClause("`Fi_stockorder` ASC");
		List<TUsAttnStock> tUsAttnStocks = tUsAttnStockMapper.selectByExample(example);
		int num = tUsAttnStockMapper.countByExample(example);
		
		AcctAttnStock004Rsp rsp = new AcctAttnStock004Rsp();
		PageRspParameter pageRspParameter = new PageRspParameter();
		pageRspParameter.setRspnum(tUsAttnStocks == null?0:tUsAttnStocks.size());
		pageRspParameter.setTotalnum(num);
		rsp.setPagersp(pageRspParameter);
		
		List<AttnStockComm> cnclStocks = new ArrayList<AttnStockComm>();
		rsp.setAttnstock(cnclStocks);
		if(tUsAttnStocks !=null && !tUsAttnStocks.isEmpty()){
			AttnStockComm cnclStock = null;
			for(TUsAttnStock tUsAttnStock : tUsAttnStocks){
				cnclStock = new AttnStockComm();
				cnclStock.setStockcode(tUsAttnStock.getFsStockcode());
				cnclStock.setStockname(tUsAttnStock.getFsStockname());
				cnclStocks.add(cnclStock);
			}
		}
		return rsp;
	}
}
