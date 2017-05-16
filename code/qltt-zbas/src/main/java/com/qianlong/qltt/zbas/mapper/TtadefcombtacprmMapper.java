package com.qianlong.qltt.zbas.mapper;

import com.qianlong.qltt.zbas.domain.TtadefcombtacprmExample;
import com.qianlong.qltt.zbas.domain.TtadefcombtacprmKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtadefcombtacprmMapper {
    int countByExample(TtadefcombtacprmExample example);

    int deleteByExample(TtadefcombtacprmExample example);

    int deleteByPrimaryKey(TtadefcombtacprmKey key);

    int insert(TtadefcombtacprmKey record);

    int insertSelective(TtadefcombtacprmKey record);

    List<TtadefcombtacprmKey> selectByExample(TtadefcombtacprmExample example);

    int updateByExampleSelective(@Param("record") TtadefcombtacprmKey record, @Param("example") TtadefcombtacprmExample example);

    int updateByExample(@Param("record") TtadefcombtacprmKey record, @Param("example") TtadefcombtacprmExample example);
}