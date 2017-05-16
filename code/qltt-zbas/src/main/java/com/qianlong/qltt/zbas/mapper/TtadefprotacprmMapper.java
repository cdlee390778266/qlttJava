package com.qianlong.qltt.zbas.mapper;

import com.qianlong.qltt.zbas.domain.Ttadefprotacprm;
import com.qianlong.qltt.zbas.domain.TtadefprotacprmExample;
import com.qianlong.qltt.zbas.domain.TtadefprotacprmKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtadefprotacprmMapper {
    int countByExample(TtadefprotacprmExample example);

    int deleteByExample(TtadefprotacprmExample example);

    int deleteByPrimaryKey(TtadefprotacprmKey key);

    int insert(Ttadefprotacprm record);

    int insertSelective(Ttadefprotacprm record);

    List<Ttadefprotacprm> selectByExample(TtadefprotacprmExample example);

    Ttadefprotacprm selectByPrimaryKey(TtadefprotacprmKey key);

    int updateByExampleSelective(@Param("record") Ttadefprotacprm record, @Param("example") TtadefprotacprmExample example);

    int updateByExample(@Param("record") Ttadefprotacprm record, @Param("example") TtadefprotacprmExample example);

    int updateByPrimaryKeySelective(Ttadefprotacprm record);

    int updateByPrimaryKey(Ttadefprotacprm record);
}