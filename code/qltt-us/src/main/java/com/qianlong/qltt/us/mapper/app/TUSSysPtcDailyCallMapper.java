package com.qianlong.qltt.us.mapper.app;

import com.qianlong.qltt.us.domain.app.TUSSysPtcDailyCall;
import com.qianlong.qltt.us.domain.app.TUSSysPtcDailyCallExample;
import com.qianlong.qltt.us.domain.app.TUSSysPtcDailyCallKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSSysPtcDailyCallMapper {
    int countByExample(TUSSysPtcDailyCallExample example);

    int deleteByExample(TUSSysPtcDailyCallExample example);

    int deleteByPrimaryKey(TUSSysPtcDailyCallKey key);

    int insert(TUSSysPtcDailyCall record);

    int insertSelective(TUSSysPtcDailyCall record);

    List<TUSSysPtcDailyCall> selectByExample(TUSSysPtcDailyCallExample example);

    TUSSysPtcDailyCall selectByPrimaryKey(TUSSysPtcDailyCallKey key);

    int updateByExampleSelective(@Param("record") TUSSysPtcDailyCall record, @Param("example") TUSSysPtcDailyCallExample example);

    int updateByExample(@Param("record") TUSSysPtcDailyCall record, @Param("example") TUSSysPtcDailyCallExample example);

    int updateByPrimaryKeySelective(TUSSysPtcDailyCall record);

    int updateByPrimaryKey(TUSSysPtcDailyCall record);
}