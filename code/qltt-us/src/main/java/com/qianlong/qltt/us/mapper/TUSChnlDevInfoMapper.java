package com.qianlong.qltt.us.mapper;

import com.qianlong.qltt.us.domain.TUSChnlDevInfo;
import com.qianlong.qltt.us.domain.TUSChnlDevInfoExample;
import com.qianlong.qltt.us.domain.TUSChnlDevInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSChnlDevInfoMapper {
    int countByExample(TUSChnlDevInfoExample example);

    int deleteByExample(TUSChnlDevInfoExample example);

    int deleteByPrimaryKey(TUSChnlDevInfoKey key);

    int insert(TUSChnlDevInfo record);

    int insertSelective(TUSChnlDevInfo record);

    List<TUSChnlDevInfo> selectByExample(TUSChnlDevInfoExample example);

    TUSChnlDevInfo selectByPrimaryKey(TUSChnlDevInfoKey key);

    int updateByExampleSelective(@Param("record") TUSChnlDevInfo record, @Param("example") TUSChnlDevInfoExample example);

    int updateByExample(@Param("record") TUSChnlDevInfo record, @Param("example") TUSChnlDevInfoExample example);

    int updateByPrimaryKeySelective(TUSChnlDevInfo record);

    int updateByPrimaryKey(TUSChnlDevInfo record);
}