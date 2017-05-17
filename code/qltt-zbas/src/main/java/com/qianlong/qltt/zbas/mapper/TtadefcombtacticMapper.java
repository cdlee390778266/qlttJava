package com.qianlong.qltt.zbas.mapper;

import com.qianlong.qltt.zbas.domain.Ttadefcombtactic;
import com.qianlong.qltt.zbas.domain.TtadefcombtacticExample;
import com.qianlong.qltt.zbas.domain.TtadefcombtacticKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtadefcombtacticMapper {
    int countByExample(TtadefcombtacticExample example);

    int deleteByExample(TtadefcombtacticExample example);

    int deleteByPrimaryKey(TtadefcombtacticKey key);

    int insert(Ttadefcombtactic record);

    int insertSelective(Ttadefcombtactic record);

    List<Ttadefcombtactic> selectByExample(TtadefcombtacticExample example);

    Ttadefcombtactic selectByPrimaryKey(TtadefcombtacticKey key);

    int updateByExampleSelective(@Param("record") Ttadefcombtactic record, @Param("example") TtadefcombtacticExample example);

    int updateByExample(@Param("record") Ttadefcombtactic record, @Param("example") TtadefcombtacticExample example);

    int updateByPrimaryKeySelective(Ttadefcombtactic record);

    int updateByPrimaryKey(Ttadefcombtactic record);
}