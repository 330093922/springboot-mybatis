package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.model.WaybillOrderRoute;
import com.thundersdata.backend.basic.service.WaybillOrderRouteService;
import com.thundersdata.backend.basic.service.WaybillOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname WaybillOrderRouteController
 * @Description TODO
 * @Date 2019/12/17 15:58
 * @Created wrc
 */
@Api(tags = "运单线路接口")
@RestController
@RequestMapping("WaybillOrderRoute")
public class WaybillOrderRouteController {

    @Autowired
    private WaybillOrderRouteService waybillOrderRouteService;


    /**
     * 新增运单线路
     *
     * @param record
     * @return
     */
    @ApiOperation(value = "添加电子运单线路", notes = "运单id，线路id 必填")
    @PostMapping("add")
    public int insertSelective(@RequestBody List<WaybillOrderRoute> record) {
        return waybillOrderRouteService.batchInsert(record);
    }
}
