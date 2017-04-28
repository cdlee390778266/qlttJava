package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUsGSetPushScope;
import com.qianlong.qltt.us.domain.TUsGSetPushScopeExample;
import com.qianlong.qltt.us.domain.TUsGSetPushScopeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUsGSetPushScopeMapper {
    int countByExample(TUsGSetPushScopeExample example);

    int deleteByExample(TUsGSetPushScopeExample example);

    int deleteByPrimaryKey(TUsGSetPushScopeKey key);

    int insert(TUsGSetPushScope record);

    int insertSelective(TUsGSetPushScope record);

    List<TUsGSetPushScope> selectByExample(TUsGSetPushScopeExample example);

    TUsGSetPushScope selectByPrimaryKey(TUsGSetPushScopeKey key);

    int updateByExampleSelective(@Param("record") TUsGSetPushScope record, @Param("example") TUsGSetPushScopeExample example);

    int updateByExample(@Param("record") TUsGSetPushScope record, @Param("example") TUsGSetPushScopeExample example);

    int updateByPrimaryKeySelective(TUsGSetPushScope record);

    int updateByPrimaryKey(TUsGSetPushScope record);
}