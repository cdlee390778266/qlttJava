package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUsAttnTacTic;
import com.qianlong.qltt.us.domain.TUsAttnTacTicExample;
import com.qianlong.qltt.us.domain.TUsAttnTacTicKey;

public interface TUsAttnTacTicMapper {
    int countByExample(TUsAttnTacTicExample example);

    int deleteByExample(TUsAttnTacTicExample example);

    int deleteByPrimaryKey(TUsAttnTacTicKey key);

    int insert(TUsAttnTacTic record);

    int insertSelective(TUsAttnTacTic record);

    List<TUsAttnTacTic> selectByExample(TUsAttnTacTicExample example);

    TUsAttnTacTic selectByPrimaryKey(TUsAttnTacTicKey key);

    int updateByExampleSelective(@Param("record") TUsAttnTacTic record, @Param("example") TUsAttnTacTicExample example);

    int updateByExample(@Param("record") TUsAttnTacTic record, @Param("example") TUsAttnTacTicExample example);

    int updateByPrimaryKeySelective(TUsAttnTacTic record);

    int updateByPrimaryKey(TUsAttnTacTic record);

    /**批量插入记录*/
	int batchInsert(List<TUsAttnTacTic> usAttnTacTics);
}