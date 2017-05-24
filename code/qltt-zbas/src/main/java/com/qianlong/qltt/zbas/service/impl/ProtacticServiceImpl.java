package com.qianlong.qltt.zbas.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.Ttadefprotacprm;
import com.qianlong.qltt.zbas.domain.TtadefprotacprmExample;
import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.TtadefprotacticExample;
import com.qianlong.qltt.zbas.mapper.TtadefprotacprmMapper;
import com.qianlong.qltt.zbas.mapper.TtadefprotacticMapper;
import com.qianlong.qltt.zbas.service.IProtacticService;
import com.qianlong.qltt.zbas.util.StringUtil;

@Service
public class ProtacticServiceImpl implements IProtacticService {
	
	@Autowired
	private TtadefprotacticMapper ttadefprotacticMapper;
	
	@Autowired
	private TtadefprotacprmMapper ttadefprotacprmMapper;

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> selectProtacticPageData(Ttadefprotactic ttadefprotactic, Pagination pagination) {
		Map<String , Object> map = new HashMap<String,Object>();
		
		TtadefprotacticExample example = new TtadefprotacticExample();
		TtadefprotacticExample.Criteria criteria = example.createCriteria();
		String fsTactic = ttadefprotactic.getFsTactic();
		if(!StringUtil.nullOrBlank(fsTactic)){
			criteria.andFsTacticLike(StringUtil.sqlLike(fsTactic));
		}
		Integer fiType = ttadefprotactic.getFiType();
		if(fiType != null)
			criteria.andFiTypeEqualTo(fiType);
		
		String fsname = ttadefprotactic.getFsName();
		if(!StringUtil.nullOrBlank(fsname)){
			criteria.andFsNameLike(StringUtil.sqlLike(fsname));
		}
		
		int total = ttadefprotacticMapper.countByExample(example);
		map.put("total", total);
		if(pagination != null){
			example.setLimitStart((pagination.getPage()-1)*pagination.getRows());
			example.setLimitEnd(pagination.getRows());
		}
		map.put("rows", ttadefprotacticMapper.selectByExample(example));
		return map;
	}

	@Override
	@Transactional
	public int insertProtactic(Ttadefprotactic ttadefprotactic) {
		return ttadefprotacticMapper.insertSelective(ttadefprotactic);
	}

	@Override
	@Transactional
	public int updateProtactic(Ttadefprotactic ttadefprotactic) {
		return ttadefprotacticMapper.updateByPrimaryKey(ttadefprotactic);
	}

	@Override
	@Transactional
	public int deleteProtactic(Ttadefprotactic ttadefprotactic) {
		//将原生指标删除
		int num = ttadefprotacticMapper.deleteByPrimaryKey(ttadefprotactic);
		//将原生指标的参数删除
		TtadefprotacprmExample example = new TtadefprotacprmExample();
		TtadefprotacprmExample.Criteria criteria = example.createCriteria();
		criteria.andFsTacticEqualTo(ttadefprotactic.getFsTactic());
		ttadefprotacprmMapper.deleteByExample(example);
		return num;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> selectProtacprmPageData(Ttadefprotacprm tadefprotacprm, Pagination pagination) {
		Map<String , Object> map = new HashMap<String,Object>();	
		TtadefprotacprmExample example = new TtadefprotacprmExample();
		TtadefprotacprmExample.Criteria criteria = example.createCriteria();
		String fsTactic = tadefprotacprm.getFsTactic();
		if(!StringUtil.nullOrBlank(fsTactic)){
			criteria.andFsTacticLike(StringUtil.sqlLike(fsTactic));
		}
		Integer fiTacticprm = tadefprotacprm.getFiTacticprm();
		if(fiTacticprm != null)
			criteria.andFiTacticprmEqualTo(fiTacticprm);
		
		String fsTacprmname = tadefprotacprm.getFsTacprmname();
		if(!StringUtil.nullOrBlank(fsTacprmname)){
			criteria.andFsTacprmnameLike(StringUtil.sqlLike(fsTacprmname));
		}
		
		int total = ttadefprotacprmMapper.countByExample(example);
		map.put("total", total);
		if(pagination != null){
			example.setLimitStart((pagination.getPage()-1)*pagination.getRows());
			example.setLimitEnd(pagination.getRows());
		}
		map.put("rows", ttadefprotacprmMapper.selectByExample(example));
		return map;
	}

	@Override
	@Transactional
	public int insertProtacprm(Ttadefprotacprm ttadefprotacprm) {
		return ttadefprotacprmMapper.insert(ttadefprotacprm);
	}

	@Override
	@Transactional
	public int updateProtacprm(Ttadefprotacprm ttadefprotacprm) {
		return ttadefprotacprmMapper.updateByPrimaryKey(ttadefprotacprm);
	}

	@Override
	@Transactional
	public int deleteProtacprm(Ttadefprotacprm ttadefprotacprm) {
		return ttadefprotacprmMapper.deleteByPrimaryKey(ttadefprotacprm);
	}
}
