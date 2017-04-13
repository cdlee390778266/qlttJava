package com.qianlong.qltt.us.mapper.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.app.TUsSysPtcCallTmp;
import com.qianlong.qltt.us.domain.app.TUsSysPtcCallTmpExample;
import com.qianlong.qltt.us.domain.app.TUsSysPtcCallTmpKey;
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

    //TODO 清空表中所有记录
	int deleteAll();

	int batchInsert(List<InterfaceCallInfo> infos);

	List<InterfaceCallInfo> selectByAppid(String appid);
}