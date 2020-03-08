package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillGoodsDetectionDTO;
import com.thundersdata.backend.basic.model.WaybillGoodsDetection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillGoodsDetectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillGoodsDetection record);

    int insertSelective(WaybillGoodsDetection record);

    WaybillGoodsDetection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillGoodsDetection record);

    int updateByPrimaryKey(WaybillGoodsDetection record);

    /**
     * 根据危险化学品货物id,删除检查项信息
     * @param goodsId
     * @return
     */
    int deleteByGoodsId(Integer goodsId);

    /**
     * 根据危险化学品货物id,查询检查项信息
     * @param goodsId
     * @return
     */
    List<WaybillGoodsDetectionDTO> selectByGoodsId(Integer goodsId);

    /**
     * 批量插入危险化学品检查项信息
     * @param record
     * @return
     */
    int insertWaybillGoodsDetectionBatch(@Param("record") List<WaybillGoodsDetection> record);
}