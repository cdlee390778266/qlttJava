package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUsSysPtcCallTmp;
import com.qianlong.qltt.us.domain.TUsSysPtcCallTmpExample;
import com.qianlong.qltt.us.domain.TUsSysPtcCallTmpKey;
import com.qianlong.qltt.us.domain.comm.InterfaceCallInfo;

public interface TUsSysPtcCallTmpMapper {
    int countByExample(TUsSysPtcCallTmpExample example);

    int deleteByExample(TUsSysPtcCallTmpExample example);

    int deleteByPrimaryKey(TUsSysPtcCallTmpKey key);

    int insert(TUsSysPtcCallTmp record);

    int insertSelective(TUsSysPtcCallTmp record);

    List<TUsSysPtcCallTmp> selectByExample(TUsSysPtcCallTmpExample example);

    TUsSysPtcCallTmp selectByPrimaryKey(TUsSysPtcCallTmpKey key);

    int updateByExampleSelective(@Param("record") TUsSysPtcCallTmp record, @Param("example") TUsSysPtcCallTmpExample example);

    int updateByExample(@Param("record") TUsSysPtcCallTmp record, @Param("example") TUsSysPtcCallTmpExample example);

    int updateByPrimaryKeySelective(TUsSysPtcCallTmp record);

    int updateByPrimaryKey(TUsSysPtcCallTmp record);

	List<InterfaceCallInfo> selectByAppid(String appid);

	void deleteAll();

	void batchInsert(List<InterfaceCallInfo> infos);
}