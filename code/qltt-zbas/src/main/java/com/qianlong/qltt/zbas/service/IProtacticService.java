package com.qianlong.qltt.zbas.service;

import java.util.Map;

import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.Ttadefprotacprm;
import com.qianlong.qltt.zbas.domain.Ttadefprotactic;

public interface IProtacticService {

	Map<String, Object> selectProtacticPageData(Ttadefprotactic ttadefprotactic, Pagination pagination);

	int insertProtactic(Ttadefprotactic ttadefprotactic);

	int updateProtactic(Ttadefprotactic ttadefprotactic);

	int deleteProtactic(Ttadefprotactic ttadefprotactic);

	Map<String, Object> selectProtacprmPageData(Ttadefprotacprm tadefprotacprm, Pagination pagination);

	int insertProtacprm(Ttadefprotacprm ttadefprotacprm);

	int updateProtacprm(Ttadefprotacprm ttadefprotacprm);

	int deleteProtacprm(Ttadefprotacprm ttadefprotacprm);

}
