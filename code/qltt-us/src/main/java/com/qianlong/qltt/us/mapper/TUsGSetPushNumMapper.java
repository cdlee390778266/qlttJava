package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUsGSetPushNum;
import com.qianlong.qltt.us.domain.TUsGSetPushNumExample;
import com.qianlong.qltt.us.domain.TUsGSetPushNumKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUsGSetPushNumMapper {
    int countByExample(TUsGSetPushNumExample example);

    int deleteByExample(TUsGSetPushNumExample example);

    int deleteByPrimaryKey(TUsGSetPushNumKey key);

    int insert(TUsGSetPushNum record);

    int insertSelective(TUsGSetPushNum record);

    List<TUsGSetPushNum> selectByExample(TUsGSetPushNumExample example);

    TUsGSetPushNum selectByPrimaryKey(TUsGSetPushNumKey key);

    int updateByExampleSelective(@Param("record") TUsGSetPushNum record, @Param("example") TUsGSetPushNumExample example);

    int updateByExample(@Param("record") TUsGSetPushNum record, @Param("example") TUsGSetPushNumExample example);

    int updateByPrimaryKeySelective(TUsGSetPushNum record);

    int updateByPrimaryKey(TUsGSetPushNum record);
}