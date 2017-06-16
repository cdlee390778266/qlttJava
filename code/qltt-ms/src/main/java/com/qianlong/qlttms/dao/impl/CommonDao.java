package com.qianlong.qlttms.dao.impl;


import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qianlong.qlttms.dao.IBaseCommonDao;
import com.qianlong.qlttms.dao.ICommonDao;

@Repository
public class CommonDao extends BaseCommonDao implements ICommonDao, IBaseCommonDao {

	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
