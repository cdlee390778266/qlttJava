package com.qianlong.qltt.zbas.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.TtadefcombtacprmExample;
import com.qianlong.qltt.zbas.domain.TtadefcombtacprmKey;
import com.qianlong.qltt.zbas.domain.Ttadefcombtactic;
import com.qianlong.qltt.zbas.domain.TtadefcombtacticExample;
import com.qianlong.qltt.zbas.mapper.TtadefcombtacprmMapper;
import com.qianlong.qltt.zbas.mapper.TtadefcombtacticMapper;
import com.qianlong.qltt.zbas.service.ICombtacticService;
import com.qianlong.qltt.zbas.util.StringUtil;

@Service
public class CombtacticServiceImpl implements ICombtacticService {
	
	@Autowired
	private TtadefcombtacticMapper ttadefcombtacticMapper;
	
	@Autowired
	private TtadefcombtacprmMapper ttadefcombtacprmMapper;

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> selectProtacticPageData(Ttadefcombtactic ttadefcombtactic, Pagination pagination) {
		Map<String , Object> map = new HashMap<String,Object>();
		
		TtadefcombtacticExample example = new TtadefcombtacticExample();
		TtadefcombtacticExample.Criteria criteria = example.createCriteria();
		String fsTactic = ttadefcombtactic.getFsTactic();//指标
		if(!StringUtil.nullOrBlank(fsTactic)){
			criteria.andFsTacticLike(StringUtil.sqlLike(fsTactic));
		}
		Integer fiMaxdelay = ttadefcombtactic.getFiMaxdelay();
		if(fiMaxdelay != null)
			criteria.andFiMaxdelayEqualTo(fiMaxdelay);
		
		int total = ttadefcombtacticMapper.countByExample(example);
		map.put("total", total);
		if(pagination != null){
			example.setLimitStart((pagination.getPage()-1)*pagination.getRows());
			example.setLimitEnd(pagination.getRows());
		}
		map.put("rows", ttadefcombtacticMapper.selectByExample(example));
		return map;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> selectProtacticPageData(TtadefcombtacprmKey ttadefcombtacprmKey, Pagination pagination) {
		Map<String , Object> map = new HashMap<String,Object>();
		TtadefcombtacprmExample example = new TtadefcombtacprmExample();
		TtadefcombtacprmExample.Criteria criteria = example.createCriteria();
		String fsTactic = ttadefcombtacprmKey.getFsTactic();//指标
		if(!StringUtil.nullOrBlank(fsTactic)){
			criteria.andFsTacticLike(StringUtil.sqlLike(fsTactic));
		}
		Integer fiSrctacticprm = ttadefcombtacprmKey.getFiSrctacticprm();
		if(fiSrctacticprm != null)
			criteria.andFiSrctacticprmEqualTo(fiSrctacticprm);
		
		String fsSrctactic = ttadefcombtacprmKey.getFsSrctactic();//指标
		if(!StringUtil.nullOrBlank(fsSrctactic)){
			criteria.andFsSrctacticLike(StringUtil.sqlLike(fsSrctactic));
		}
		int total = ttadefcombtacprmMapper.countByExample(example);
		map.put("total", total);
		if(pagination != null){
			example.setLimitStart((pagination.getPage()-1)*pagination.getRows());
			example.setLimitEnd(pagination.getRows());
		}
		map.put("rows", ttadefcombtacprmMapper.selectByExample(example));
		return map;
	}
}
