package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.vo.WaybillGoodsDetectionVO;

import java.util.List;

/**
 * 危险化学品货物-检查项管理
 */
public interface WaybillGoodsDetectionService {

    /**
     * 新增危险化学品检查项信息
     * @param waybillGoodsDetectionVo
     * @return
     */
    int insertWaybillGoodsDetection(WaybillGoodsDetectionVO waybillGoodsDetectionVo);


    /**
     * 根据货物id删除危险化学品货物检查项信息
     * @param goodsId
     * @return
     */
    int deleteWaybillGoodsDetectionByGoodsId(Integer goodsId);


    /**
     * 根据货物id添加或修改危险化学品货物检查项信息
     * @param listWaybillGoodsDetectionVO
     * @return
     */
    int deleteAndInsertWaybillGoodsDetectionByGoodsId(Integer goodsId, List<WaybillGoodsDetectionVO> listWaybillGoodsDetectionVO);

}
