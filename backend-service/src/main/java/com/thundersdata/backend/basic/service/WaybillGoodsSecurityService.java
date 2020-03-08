package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.vo.WaybillGoodsSecurityVO;

/**
 * 危险化学品货物-安全卡管理
 */
public interface WaybillGoodsSecurityService {

    /**
     * 新增危险化学品安全卡信息
     * @param waybillGoodsSecurityVo
     * @return
     */
    int insertWaybillGoodsSecurity(WaybillGoodsSecurityVO waybillGoodsSecurityVo);

    /**
     * 根据货物id删除危险化学品货物安全卡信息
     * @param goodsId
     * @return
     */
    int deleteWaybillGoodsSecurityByGoodsId(Integer goodsId);

    /**
     * 根据货物id添加或修改危险化学品货物安全卡信息
     * @param waybillGoodsSecurityVo
     * @return
     */
    int deleteAndInsertWaybillGoodsSecurityByGoodsId(Integer goodsId, WaybillGoodsSecurityVO waybillGoodsSecurityVo);

}
