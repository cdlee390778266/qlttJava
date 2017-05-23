package com.qianlong.qltt.zbas.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.qianlong.qltt.zbas.common.JsonResult;
import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.Ttagrpprotac;
import com.qianlong.qltt.zbas.domain.TtagrpprotacExample;
import com.qianlong.qltt.zbas.domain.Ttagrpprotacmem;
import com.qianlong.qltt.zbas.domain.TtagrpprotacmemExample;
import com.qianlong.qltt.zbas.mapper.TtagrpprotacMapper;
import com.qianlong.qltt.zbas.mapper.TtagrpprotacmemMapper;
import com.qianlong.qltt.zbas.service.IGrpprotacService;
import com.qianlong.qltt.zbas.util.StringUtil;


@Service
public class GrpprotacServiceImpl implements IGrpprotacService {
	
	private static  final Logger logger = LoggerFactory.getLogger(GrpprotacServiceImpl.class);
	@Autowired
	private TtagrpprotacMapper ttagrpprotacMapper;
	
	@Autowired
	private TtagrpprotacmemMapper ttagrpprotacmemMapper;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Ttagrpprotac> selectGrpprotacTree(Integer fiLevel, String fSPtacgroup,String fSPtacgroupName) {
		TtagrpprotacExample example = new TtagrpprotacExample();
		TtagrpprotacExample.Criteria criteria = example.createCriteria();
		if(fiLevel != null){
			criteria.andFiLevelEqualTo(fiLevel);
		}
		if(!StringUtil.nullOrBlank(fSPtacgroup)){
			criteria.andFsPtacgroupEqualTo(fSPtacgroup.trim());
		}
		List<Ttagrpprotac> ttagrpprotacs = ttagrpprotacMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(ttagrpprotacs)){
			for(Ttagrpprotac ttagrpprotac:ttagrpprotacs){
				//设置父级指标组的名称
				ttagrpprotac.setFsPtacgroupName(fSPtacgroupName);
				//加载子元素
				List<Ttagrpprotac> children = selectGrpprotacTree(null, ttagrpprotac.getFsTacgroup(),ttagrpprotac.getFsName());
				ttagrpprotac.setChildren(children);
				//加载成员
				List<Ttagrpprotacmem> ttagrpprotacmems = ttagrpprotacmemMapper.selectMemsByFsTacgroup(ttagrpprotac.getFsTacgroup());
				ttagrpprotac.setMember(ttagrpprotacmems);	
			}
		}
		return ttagrpprotacs;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Ttadefprotactic> selectUnselectZB(String grpcode) {
		return ttagrpprotacmemMapper.selectUnselectZB(grpcode);
	}

	@Override
	@Transactional(readOnly=true)
	public String checkGrpCode(Ttagrpprotac ttagrpprotac) {
		Ttagrpprotac fromDb = ttagrpprotacMapper.selectByPrimaryKey(ttagrpprotac);
		return fromDb==null?"true":"false";
	}

	@Override
	@Transactional
	public Map<String, Object> saveTacgroup(Ttagrpprotac ttagrpprotac, List<Ttagrpprotacmem> mems , String oper) {
		if("ADD".equals(oper)){
			return addTtagrpprotac(ttagrpprotac,mems);
		}else if("EDIT".equals(oper)){
			return eidtTtagrpprotac(ttagrpprotac,mems);
		}else if("DELETE".equals(oper)){
			return deleteTtagrpprotac(ttagrpprotac);
		}else{
			return JsonResult.jsonError("未知操作类型");
		}
	}

	//删除指标组信息，因为是一个树形结构，删除它，也会删除子节点
	private Map<String, Object> deleteTtagrpprotac(Ttagrpprotac ttagrpprotac) {
		//删除子节点
		TtagrpprotacExample example = new TtagrpprotacExample();
		TtagrpprotacExample.Criteria criteria = example.createCriteria();
		criteria.andFsPtacgroupEqualTo(ttagrpprotac.getFsTacgroup());
		List<Ttagrpprotac> children = ttagrpprotacMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(children)){
			for(Ttagrpprotac child:children){
				deleteTtagrpprotac(child);
			}
		}
		//删除组成员
		deleteTtagrpprotacmemByFsTacgroup(ttagrpprotac.getFsTacgroup());
		//删除本身这条记录
		ttagrpprotacMapper.deleteByPrimaryKey(ttagrpprotac);
		return JsonResult.jsonOk();
	}

	//根据信息，修改一个指标组
	private Map<String, Object> eidtTtagrpprotac(Ttagrpprotac ttagrpprotac, List<Ttagrpprotacmem> mems ) {
		//修改指标信息
		int num = ttagrpprotacMapper.updateByPrimaryKey(ttagrpprotac);
		//删除指标组成员，然后添加
		if(num != 0){
			deleteTtagrpprotacmemByFsTacgroup(ttagrpprotac.getFsTacgroup());
			//插入指标组成员
			batchInsertTtagrpprotacmems(mems);
		}
		return JsonResult.jsonOk();
	}

	//根据信息，新增一个指标组
	private Map<String, Object> addTtagrpprotac(Ttagrpprotac ttagrpprotac,  List<Ttagrpprotacmem> mems ) {
		//插入指标组信息
		try {
			ttagrpprotacMapper.insert(ttagrpprotac);
		} catch (DuplicateKeyException e) {
			return JsonResult.jsonError("该指标组编号已存在");
		}
		batchInsertTtagrpprotacmems(mems);
		return JsonResult.jsonOk();
	}
	
	private void deleteTtagrpprotacmemByFsTacgroup(String fsTacgroup){
		TtagrpprotacmemExample example = new TtagrpprotacmemExample();
		TtagrpprotacmemExample.Criteria criteria = example.createCriteria();
		criteria.andFsTacgroupEqualTo(fsTacgroup);
		ttagrpprotacmemMapper.deleteByExample(example);
	}
	private void batchInsertTtagrpprotacmems( List<Ttagrpprotacmem> mems){
		if(!CollectionUtils.isEmpty(mems)){
			for(Ttagrpprotacmem mem:mems){
				ttagrpprotacmemMapper.insert(mem);
			}
		}
	}
}
