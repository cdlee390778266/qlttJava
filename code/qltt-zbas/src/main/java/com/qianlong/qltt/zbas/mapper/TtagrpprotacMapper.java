package com.qianlong.qltt.zbas.mapper;

import com.qianlong.qltt.zbas.domain.Ttagrpprotac;
import com.qianlong.qltt.zbas.domain.TtagrpprotacExample;
import com.qianlong.qltt.zbas.domain.TtagrpprotacKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtagrpprotacMapper {
    int countByExample(TtagrpprotacExample example);

    int deleteByExample(TtagrpprotacExample example);

    int deleteByPrimaryKey(TtagrpprotacKey key);

    int insert(Ttagrpprotac record);

    int insertSelective(Ttagrpprotac record);

    List<Ttagrpprotac> selectByExample(TtagrpprotacExample example);

    Ttagrpprotac selectByPrimaryKey(TtagrpprotacKey key);

    int updateByExampleSelective(@Param("record") Ttagrpprotac record, @Param("example") TtagrpprotacExample example);

    int updateByExample(@Param("record") Ttagrpprotac record, @Param("example") TtagrpprotacExample example);

    int updateByPrimaryKeySelective(Ttagrpprotac record);

    int updateByPrimaryKey(Ttagrpprotac record);
}