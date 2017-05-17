package com.qianlong.qltt.zbas.service;

import java.util.List;
import java.util.Map;

import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.Ttagrpprotac;
import com.qianlong.qltt.zbas.domain.Ttagrpprotacmem;

public interface IGrpprotacService {
	/**获取指标组树*/
	List<Ttagrpprotac> selectGrpprotacTree(Integer fiLevel, String fSPtacgroup,String fSPtacgroupName);

	/**选择未选中的指标*/
	List<Ttadefprotactic> selectUnselectZB(String grpcode);

	String checkGrpCode(Ttagrpprotac ttagrpprotac);

	/**对指标组进行操作，包括新增、编辑、删除*/
	Map<String, Object> saveTacgroup(Ttagrpprotac ttagrpprotac, List<Ttagrpprotacmem> list, String oper);
}
