package com.thundersdata.backend.basic.dao;

import com.thundersdata.backend.basic.model.WaybillOrderRoute;

import com.thundersdata.backend.basic.model.WaybillOrderRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WaybillOrderRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillOrderRoute record);

    /**
     *新增运单线路
     * @param record
     * @return
     */
    int batchInsert(List<WaybillOrderRoute> record);

    WaybillOrderRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillOrderRoute record);

    int updateByPrimaryKey(WaybillOrderRoute record);
}