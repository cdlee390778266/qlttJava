package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUsCombTacMenu;
import com.qianlong.qltt.us.domain.TUsCombTacMenuExample;
import com.qianlong.qltt.us.domain.TUsCombTacMenuKey;

public interface TUsCombTacMenuMapper {
    int countByExample(TUsCombTacMenuExample example);

    int deleteByExample(TUsCombTacMenuExample example);

    int deleteByPrimaryKey(TUsCombTacMenuKey key);

    int insert(TUsCombTacMenu record);

    int insertSelective(TUsCombTacMenu record);

    List<TUsCombTacMenu> selectByExample(TUsCombTacMenuExample example);

    TUsCombTacMenu selectByPrimaryKey(TUsCombTacMenuKey key);

    int updateByExampleSelective(@Param("record") TUsCombTacMenu record, @Param("example") TUsCombTacMenuExample example);

    int updateByExample(@Param("record") TUsCombTacMenu record, @Param("example") TUsCombTacMenuExample example);

    int updateByPrimaryKeySelective(TUsCombTacMenu record);

    int updateByPrimaryKey(TUsCombTacMenu record);
    
    /**获取该账号下最大的order*/
	int selectMaxOrderByTtacct(String ttacct);
}