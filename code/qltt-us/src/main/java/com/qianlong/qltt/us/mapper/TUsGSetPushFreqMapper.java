package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUsGSetPushFreq;
import com.qianlong.qltt.us.domain.TUsGSetPushFreqExample;
import com.qianlong.qltt.us.domain.TUsGSetPushFreqKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUsGSetPushFreqMapper {
    int countByExample(TUsGSetPushFreqExample example);

    int deleteByExample(TUsGSetPushFreqExample example);

    int deleteByPrimaryKey(TUsGSetPushFreqKey key);

    int insert(TUsGSetPushFreq record);

    int insertSelective(TUsGSetPushFreq record);

    List<TUsGSetPushFreq> selectByExample(TUsGSetPushFreqExample example);

    TUsGSetPushFreq selectByPrimaryKey(TUsGSetPushFreqKey key);

    int updateByExampleSelective(@Param("record") TUsGSetPushFreq record, @Param("example") TUsGSetPushFreqExample example);

    int updateByExample(@Param("record") TUsGSetPushFreq record, @Param("example") TUsGSetPushFreqExample example);

    int updateByPrimaryKeySelective(TUsGSetPushFreq record);

    int updateByPrimaryKey(TUsGSetPushFreq record);
}