package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.model.WaybillOrderNode;
import org.apache.ibatis.annotations.Param;

public interface WaybillOrderNodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillOrderNode record);

    int insertSelective(WaybillOrderNode record);

    WaybillOrderNode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillOrderNode record);

    int updateByPrimaryKeyWithBLOBs(WaybillOrderNode record);

    int updateByPrimaryKey(WaybillOrderNode record);

    /**
     * 删除订单所有历史节点
     *
     * @param orderCode 订单号
     * @return
     */
    int deleteWaybillOrderNode(@Param("orderCode") String orderCode);
}