package com.qianlong.qltt.us.mapper.app;

import com.qianlong.qltt.us.domain.app.TUSSysPltDailyCall;
import com.qianlong.qltt.us.domain.app.TUSSysPltDailyCallExample;
import com.qianlong.qltt.us.domain.app.TUSSysPltDailyCallKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSSysPltDailyCallMapper {
    int countByExample(TUSSysPltDailyCallExample example);

    int deleteByExample(TUSSysPltDailyCallExample example);

    int deleteByPrimaryKey(TUSSysPltDailyCallKey key);

    int insert(TUSSysPltDailyCall record);

    int insertSelective(TUSSysPltDailyCall record);

    List<TUSSysPltDailyCall> selectByExample(TUSSysPltDailyCallExample example);

    TUSSysPltDailyCall selectByPrimaryKey(TUSSysPltDailyCallKey key);

    int updateByExampleSelective(@Param("record") TUSSysPltDailyCall record, @Param("example") TUSSysPltDailyCallExample example);

    int updateByExample(@Param("record") TUSSysPltDailyCall record, @Param("example") TUSSysPltDailyCallExample example);

    int updateByPrimaryKeySelective(TUSSysPltDailyCall record);

    int updateByPrimaryKey(TUSSysPltDailyCall record);
}