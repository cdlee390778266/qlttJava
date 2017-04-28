package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUSBindRel;
import com.qianlong.qltt.us.domain.TUSBindRelExample;
import com.qianlong.qltt.us.domain.TUSBindRelKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSBindRelMapper {
    int countByExample(TUSBindRelExample example);

    int deleteByExample(TUSBindRelExample example);

    int deleteByPrimaryKey(TUSBindRelKey key);

    int insert(TUSBindRel record);

    int insertSelective(TUSBindRel record);

    List<TUSBindRel> selectByExample(TUSBindRelExample example);

    TUSBindRel selectByPrimaryKey(TUSBindRelKey key);

    int updateByExampleSelective(@Param("record") TUSBindRel record, @Param("example") TUSBindRelExample example);

    int updateByExample(@Param("record") TUSBindRel record, @Param("example") TUSBindRelExample example);

    int updateByPrimaryKeySelective(TUSBindRel record);

    int updateByPrimaryKey(TUSBindRel record);
}