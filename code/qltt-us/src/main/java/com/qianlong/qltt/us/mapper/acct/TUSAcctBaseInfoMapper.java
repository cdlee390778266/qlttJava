package com.qianlong.qltt.us.mapper.acct;

import com.qianlong.qltt.us.domain.acct.TUSAcctBaseInfo;
import com.qianlong.qltt.us.domain.acct.TUSAcctBaseInfoExample;
import com.qianlong.qltt.us.domain.acct.TUSAcctBaseInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUSAcctBaseInfoMapper {
    int countByExample(TUSAcctBaseInfoExample example);

    int deleteByExample(TUSAcctBaseInfoExample example);

    int deleteByPrimaryKey(TUSAcctBaseInfoKey key);

    int insert(TUSAcctBaseInfo record);

    int insertSelective(TUSAcctBaseInfo record);

    List<TUSAcctBaseInfo> selectByExample(TUSAcctBaseInfoExample example);

    TUSAcctBaseInfo selectByPrimaryKey(TUSAcctBaseInfoKey key);

    int updateByExampleSelective(@Param("record") TUSAcctBaseInfo record, @Param("example") TUSAcctBaseInfoExample example);

    int updateByExample(@Param("record") TUSAcctBaseInfo record, @Param("example") TUSAcctBaseInfoExample example);

    int updateByPrimaryKeySelective(TUSAcctBaseInfo record);

    int updateByPrimaryKey(TUSAcctBaseInfo record);
}