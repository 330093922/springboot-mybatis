package com.thundersdata.backend.basic.dao;

import com.thundersdata.backend.basic.dto.WaybillGoodsDisciplineDTO;
import com.thundersdata.backend.basic.model.WaybillGoodsDiscipline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillGoodsDisciplineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillGoodsDiscipline record);

    int insertSelective(WaybillGoodsDiscipline record);

    WaybillGoodsDiscipline selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillGoodsDiscipline record);

    int updateByPrimaryKeyWithBLOBs(WaybillGoodsDiscipline record);

    int updateByPrimaryKey(WaybillGoodsDiscipline record);

    /**
     * 根据危险化学品货物id,删除操作规程信息
     * @param goodsId
     * @return
     */
    int deleteByGoodsId(Integer goodsId);

    /**
     * 根据危险化学品货物id,查询操作规程信息
     * @param goodsId
     * @return
     */
    List<WaybillGoodsDisciplineDTO> selectByGoodsId(Integer goodsId);

    /**
     * 批量插入危险化学品货物操作规程信息
     * @param record
     * @return
     */
    int insertWaybillGoodsDisciplineBatch(@Param("record") List<WaybillGoodsDiscipline> record);
}