package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.dto.WaybillConsignGoodsDTO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsInsertVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsUpdateVO;
import com.thundersdata.backend.basic.vo.WaybillConsignGoodsVO;

import java.util.List;

/**
 * @Classname WaybillConsignGoodsService
 * @Description 托运单货物关联service
 * @Date 2020-2-1
 * @Created wanghaibo
 */
public interface WaybillConsignGoodsService {

    /**
     * 插入托运单货运关联
     * @param waybillConsignGoodsVO
     * @return
     */
    int insertWaybillConsignGoods(WaybillConsignGoodsInsertVO waybillConsignGoodsVO);

    /**
     * 批量插入托运单货物关联信息
     * @param list
     * @return
     */
    int insertWaybillConsignGoodsBatch(Integer consignId, List<WaybillConsignGoodsInsertVO> list);

    /**
     * 修改托运单的货物关联信息
     * @param consignId
     * @param list
     * @return
     */
    int updateWaybillConsignGoodsBatch(Integer consignId,List<WaybillConsignGoodsUpdateVO> list);

    /**
     * 查询托运单关联的所有货物信息
     * @param consignId
     * @return
     */
    List<WaybillConsignGoodsDTO> selectConsignGoodsList(Integer consignId);
}
