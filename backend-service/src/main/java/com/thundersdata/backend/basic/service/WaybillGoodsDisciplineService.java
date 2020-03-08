package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.vo.WaybillGoodsDisciplineVO;

import java.util.List;

/**
 * 危险化学品货物-操作规程管理
 */
public interface WaybillGoodsDisciplineService {

    /**
     * 新增危险化学品操作规程信息
     * @param waybillGoodsDisciplineVo
     * @return
     */
    int insertWaybillGoodsDiscipline(WaybillGoodsDisciplineVO waybillGoodsDisciplineVo);


    /**
     * 根据货物id删除危险化学品货物操作规程信息
     * @param goodsId
     * @return
     */
    int deleteWaybillGoodsDisciplineByGoodsId(Integer goodsId);


    /**
     * 根据货物id添加或修改危险化学品货物操作规程信息
     * @param listWaybillGoodsDisciplineVo
     * @return
     */
    int deleteAndInsertWaybillGoodsDisciplineByGoodsId(Integer goodsId, List<WaybillGoodsDisciplineVO> listWaybillGoodsDisciplineVo);

}
