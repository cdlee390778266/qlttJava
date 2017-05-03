package com.qianlong.qltt.us.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.us.domain.TUsAttnStock;
import com.qianlong.qltt.us.domain.TUsAttnStockExample;
import com.qianlong.qltt.us.domain.TUsAttnStockKey;

public interface TUsAttnStockMapper {
    int countByExample(TUsAttnStockExample example);

    int deleteByExample(TUsAttnStockExample example);

    int deleteByPrimaryKey(TUsAttnStockKey key);

    int insert(TUsAttnStock record);

    int insertSelective(TUsAttnStock record);

    List<TUsAttnStock> selectByExample(TUsAttnStockExample example);

    TUsAttnStock selectByPrimaryKey(TUsAttnStockKey key);

    int updateByExampleSelective(@Param("record") TUsAttnStock record, @Param("example") TUsAttnStockExample example);

    int updateByExample(@Param("record") TUsAttnStock record, @Param("example") TUsAttnStockExample example);

    int updateByPrimaryKeySelective(TUsAttnStock record);

    int updateByPrimaryKey(TUsAttnStock record);

    /**批量插入*/
	int batchInsert(List<TUsAttnStock> tUsAttnStocks);

	/**推推账户下*/
	int selectMaxOfSorckOrder(String ttacct, Integer poolindex);
}