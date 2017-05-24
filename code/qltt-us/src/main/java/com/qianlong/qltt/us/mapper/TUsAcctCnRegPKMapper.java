package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUsAcctCnRegPKKey;

public interface TUsAcctCnRegPKMapper {
	
    int deleteByPrimaryKey(TUsAcctCnRegPKKey key);

    int insert(TUsAcctCnRegPKKey record);
}