package com.qianlong.qltt.us.mapper.app;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.app.TUSSysAccessTokenTmp;
import com.qianlong.qltt.us.domain.app.TUSSysAccessTokenTmpExample;
import com.qianlong.qltt.us.domain.app.TUSSysAccessTokenTmpKey;
import com.qianlong.qltt.us.domain.comm.AccessToken;

public interface TUSSysAccessTokenTmpMapper {
    int countByExample(TUSSysAccessTokenTmpExample example);

    int deleteByExample(TUSSysAccessTokenTmpExample example);

    int deleteByPrimaryKey(TUSSysAccessTokenTmpKey key);

    int insert(TUSSysAccessTokenTmp record);

    int insertSelective(TUSSysAccessTokenTmp record);

    List<TUSSysAccessTokenTmp> selectByExample(TUSSysAccessTokenTmpExample example);

    TUSSysAccessTokenTmp selectByPrimaryKey(TUSSysAccessTokenTmpKey key);

    int updateByExampleSelective(@Param("record") TUSSysAccessTokenTmp record, @Param("example") TUSSysAccessTokenTmpExample example);

    int updateByExample(@Param("record") TUSSysAccessTokenTmp record, @Param("example") TUSSysAccessTokenTmpExample example);

    int updateByPrimaryKeySelective(TUSSysAccessTokenTmp record);

    int updateByPrimaryKey(TUSSysAccessTokenTmp record);
    //TODO 
    /**
     * 加载AcessToken数据
     */
	List<AccessToken> selectAllAccessTokens();

	/**
	 * 清空表中所有记录
	 */
	int deleteAll();

	/**
	 * 批量插入
	 */
	int batchInsert(List<AccessToken> accessTokens);
}