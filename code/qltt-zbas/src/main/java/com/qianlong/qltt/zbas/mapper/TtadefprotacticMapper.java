package com.qianlong.qltt.zbas.mapper;

import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.TtadefprotacticExample;
import com.qianlong.qltt.zbas.domain.TtadefprotacticKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtadefprotacticMapper {
    int countByExample(TtadefprotacticExample example);

    int deleteByExample(TtadefprotacticExample example);

    int deleteByPrimaryKey(TtadefprotacticKey key);

    int insert(Ttadefprotactic record);

    int insertSelective(Ttadefprotactic record);

    List<Ttadefprotactic> selectByExample(TtadefprotacticExample example);

    Ttadefprotactic selectByPrimaryKey(TtadefprotacticKey key);

    int updateByExampleSelective(@Param("record") Ttadefprotactic record, @Param("example") TtadefprotacticExample example);

    int updateByExample(@Param("record") Ttadefprotactic record, @Param("example") TtadefprotacticExample example);

    int updateByPrimaryKeySelective(Ttadefprotactic record);

    int updateByPrimaryKey(Ttadefprotactic record);
}