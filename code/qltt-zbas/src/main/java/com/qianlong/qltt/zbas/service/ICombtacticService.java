package com.qianlong.qltt.zbas.service;

import java.util.Map;

import com.qianlong.qltt.zbas.common.pagination.Pagination;
import com.qianlong.qltt.zbas.domain.TtadefcombtacprmKey;
import com.qianlong.qltt.zbas.domain.Ttadefcombtactic;

public interface ICombtacticService {

	Map<String,Object> selectProtacticPageData(Ttadefcombtactic ttadefcombtactic, Pagination pagination);

	Map<String,Object> selectProtacticPageData(TtadefcombtacprmKey ttadefcombtacprmKey, Pagination pagination);

}
