package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.lang.Assert;
import com.thundersdata.backend.basic.dao.WaybillOrderRouteMapper;
import com.thundersdata.backend.basic.model.WaybillOrderRoute;
import com.thundersdata.backend.basic.service.WaybillOrderRouteService;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname WaybillOrderRouteServiceImpl
 * @Description TODO
 * @Date 2019/12/17 15:56
 * @Created wrc
 */
@Service
public class WaybillOrderRouteServiceImpl implements WaybillOrderRouteService {

    @Autowired
    private WaybillOrderRouteMapper waybillOrderRouteMapper;


    /**
     * 新增运单线路
     *
     * @param record
     * @return
     */
    @Override
    public int batchInsert(List<WaybillOrderRoute> record) {
        return waybillOrderRouteMapper.batchInsert(record);
    }
}
