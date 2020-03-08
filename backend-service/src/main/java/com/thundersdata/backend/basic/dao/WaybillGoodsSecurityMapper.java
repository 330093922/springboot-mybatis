package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillGoodsSecurityDTO;
import com.thundersdata.backend.basic.model.WaybillGoodsSecurity;
import com.thundersdata.backend.basic.model.WaybillGoodsSecurityWithBLOBs;

public interface WaybillGoodsSecurityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillGoodsSecurityWithBLOBs record);

    int insertSelective(WaybillGoodsSecurityWithBLOBs record);

    WaybillGoodsSecurityWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillGoodsSecurityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(WaybillGoodsSecurityWithBLOBs record);

    int updateByPrimaryKey(WaybillGoodsSecurity record);

    /**
     * 根据危险化学品货物id,删除安全卡信息
     * @param goodsId
     * @return
     */
    int deleteByGoodsId(Integer goodsId);

    /**
     * 根据危险化学品货物id,查询安全卡信息
     * @param goodsId
     * @return
     */
    WaybillGoodsSecurityDTO selectByGoodsId(Integer goodsId);
}