package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUSAcctCNReg;
import com.qianlong.qltt.us.domain.TUSAcctCNRegExample;
import com.qianlong.qltt.us.domain.TUSAcctCNRegKey;

public interface TUSAcctCNRegMapper {
    int countByExample(TUSAcctCNRegExample example);

    int deleteByExample(TUSAcctCNRegExample example);

    int deleteByPrimaryKey(TUSAcctCNRegKey key);

    int insert(TUSAcctCNReg record);

    int insertSelective(TUSAcctCNReg record);

    List<TUSAcctCNReg> selectByExample(TUSAcctCNRegExample example);

    TUSAcctCNReg selectByPrimaryKey(TUSAcctCNRegKey key);

    int updateByExampleSelective(@Param("record") TUSAcctCNReg record, @Param("example") TUSAcctCNRegExample example);

    int updateByExample(@Param("record") TUSAcctCNReg record, @Param("example") TUSAcctCNRegExample example);

    int updateByPrimaryKeySelective(TUSAcctCNReg record);

    int updateByPrimaryKey(TUSAcctCNReg record);

	int selectMaxAcct();
}