package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.WaybillOrderGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillOrderGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillOrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillOrderGoods record);

    int insertSelective(WaybillOrderGoods record);

    WaybillOrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillOrderGoods record);

    int updateByPrimaryKeyWithBLOBs(WaybillOrderGoods record);

    int updateByPrimaryKey(WaybillOrderGoods record);

    int deleteWaybillOrderGoods(String orderCode);

    int batchInsertWaybillOrderGoods(@Param("waybillOrderGoodsDTOListList") List<WaybillOrderGoodsDTO> waybillOrderGoodsDTOListList,@Param("shipmentCreditCode") String shipmentCreditCode);
}