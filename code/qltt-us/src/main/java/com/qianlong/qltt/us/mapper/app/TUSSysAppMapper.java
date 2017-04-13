package com.qianlong.qltt.us.mapper.app;

import com.qianlong.qltt.us.domain.app.TUSSysApp;
import com.qianlong.qltt.us.domain.app.TUSSysAppExample;
import com.qianlong.qltt.us.domain.app.TUSSysAppKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSSysAppMapper {
    int countByExample(TUSSysAppExample example);

    int deleteByExample(TUSSysAppExample example);

    int deleteByPrimaryKey(TUSSysAppKey key);

    int insert(TUSSysApp record);

    int insertSelective(TUSSysApp record);

    List<TUSSysApp> selectByExample(TUSSysAppExample example);

    TUSSysApp selectByPrimaryKey(TUSSysAppKey key);

    int updateByExampleSelective(@Param("record") TUSSysApp record, @Param("example") TUSSysAppExample example);

    int updateByExample(@Param("record") TUSSysApp record, @Param("example") TUSSysAppExample example);

    int updateByPrimaryKeySelective(TUSSysApp record);

    int updateByPrimaryKey(TUSSysApp record);
}