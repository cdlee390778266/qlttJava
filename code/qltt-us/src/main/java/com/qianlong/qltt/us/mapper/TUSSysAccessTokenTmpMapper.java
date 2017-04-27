package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUSSysAccessTokenTmp;
import com.qianlong.qltt.us.domain.TUSSysAccessTokenTmpExample;
import com.qianlong.qltt.us.domain.TUSSysAccessTokenTmpKey;
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

	List<AccessToken> selectAllAccessTokens();

	void deleteAll();

	void batchInsert(List<AccessToken> accessTokens);

}