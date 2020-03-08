package com.thundersdata.backend.basic.service;

import com.thundersdata.backend.basic.model.WaybillOrderRoute;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname WaybillOrderRouteService
 * @Description TODO
 * @Date 2019/12/17 15:54
 * @Created wrc
 */
public interface WaybillOrderRouteService {

    /**
     *新增运单线路
     * @param record
     * @return
     */
    int batchInsert(@Param("list") List<WaybillOrderRoute> record);
}
