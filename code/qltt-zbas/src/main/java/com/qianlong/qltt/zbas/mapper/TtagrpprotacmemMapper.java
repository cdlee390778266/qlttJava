package com.qianlong.qltt.zbas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qianlong.qltt.zbas.domain.Ttadefprotactic;
import com.qianlong.qltt.zbas.domain.Ttagrpprotacmem;
import com.qianlong.qltt.zbas.domain.TtagrpprotacmemExample;
import com.qianlong.qltt.zbas.domain.TtagrpprotacmemKey;

public interface TtagrpprotacmemMapper {
    int countByExample(TtagrpprotacmemExample example);

    int deleteByExample(TtagrpprotacmemExample example);

    int deleteByPrimaryKey(TtagrpprotacmemKey key);

    int insert(Ttagrpprotacmem record);

    int insertSelective(Ttagrpprotacmem record);

    List<Ttagrpprotacmem> selectByExample(TtagrpprotacmemExample example);

    Ttagrpprotacmem selectByPrimaryKey(TtagrpprotacmemKey key);

    int updateByExampleSelective(@Param("record") Ttagrpprotacmem record, @Param("example") TtagrpprotacmemExample example);

    int updateByExample(@Param("record") Ttagrpprotacmem record, @Param("example") TtagrpprotacmemExample example);

    int updateByPrimaryKeySelective(Ttagrpprotacmem record);

    int updateByPrimaryKey(Ttagrpprotacmem record);
    
    /**根据指标组编号找到指标成员信息*/
	List<Ttagrpprotacmem> selectMemsByFsTacgroup(@Param("fsTacgroup") String fsTacgroup);

	/**查询未选中指标*/
	List<Ttadefprotactic> selectUnselectZB(@Param("grpcode") String grpcode);
}