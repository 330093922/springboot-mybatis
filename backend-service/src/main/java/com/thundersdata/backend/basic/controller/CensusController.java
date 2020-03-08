package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.CensusDTO;
import com.thundersdata.backend.basic.service.CensusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "统计分析接口")
@RestController
@RequestMapping("/census")
public class CensusController {
    @Autowired
    private CensusService censusService;

    /**
     * 接口4：新运单、运输中、已完成运单统计
     * @return
     */
    @ApiOperation(value = "接口4：新运单、运输中、已完成运单统计", notes = "接口4：新运单、运输中、已完成运单统计")
    @GetMapping("/wayBillCensus")
    public List<CensusDTO> wayBillCensus() {
        return censusService.wayBillCensus();
    }

    /**
     * 接口5：统计企业总数、车辆总数、人员总数
     * @return
     */
    @ApiOperation(value = "接口5：统计企业总数、车辆总数、人员总数", notes = "接口5：统计企业总数、车辆总数、人员总数")
    @GetMapping("/evpCensus")
    public List<CensusDTO> evpCensus() {
        return censusService.evpCensus();
    }

    /**
     * 接口6：押运员或司机的运单个数排名
     * @return
     */
    @ApiOperation(value = "接口6：押运员或司机的运单个数排名", notes = "接口6：押运员或司机的运单个数排名")
    @GetMapping("/driverCensus")
    public List<CensusDTO> driverCensus() {
        return censusService.driverCensus();
    }

    /**
     * 接口8：企业分类占比
     * @return
     */
    @ApiOperation(value = "接口8：企业分类占比", notes = "接口8：企业分类占比")
    @GetMapping("/componySortCensus")
    public List<CensusDTO> componySortCensus() {
        return censusService.componySortCensus();
    }

    /**
     * 接口7：已托运、未托运、已完成托运单统计
     * @return
     */
    @ApiOperation(value = "接口7：已托运、未托运、已完成托运单统计", notes = "接口7：已托运、未托运、已完成托运单统计")
    @GetMapping("/orderCensus")
    public List<CensusDTO> orderCensus(){
        return censusService.orderCensus();
    }

    /**
     * 接口1：统计长期未填报运单的车辆
     * @return
     */
    @ApiOperation(value = "接口1：统计长期未填报运单的车辆", notes = "接口1：统计长期未填报运单的车辆")
    @GetMapping("/vehicleCensus")
    public List<CensusDTO> vehicleCensus(){
        return censusService.vehicleCensus();
    }

    /**
     * 前四大运输公司运输占比
     * @return
     */
    @ApiOperation(value = "前四大运输公司运输占比", notes = "前四大运输公司运输占比")
    @GetMapping("/fourGreatOwner")
    public List<CensusDTO> fourGreatOwner() {
        return censusService.fourGreatOwner();
    }

    /**
     * 市企业发货与收货运单数占比
     * @return
     */
    @ApiOperation(value = "市企业发货与收货运单数占比", notes = "市企业发货与收货运单数占比")
    @GetMapping("/shipReceivePercent")
    public List<CensusDTO> shipReceivePercent() {
        return censusService.shipReceivePercent();
    }

    /**
     * 过去8天运单数统计
     * @return
     */
    @ApiOperation(value = "过去8天运单数统计", notes = "过去8天运单数统计")
    @GetMapping("/eightOrders")
    public List<CensusDTO> eightOrders() {
        return censusService.eightOrders();
    }
}