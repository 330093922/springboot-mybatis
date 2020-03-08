package com.thundersdata.backend.basic.dao;

import com.thundersdata.backend.basic.dto.CensusDTO;

import java.util.List;

public interface CensusMapper {
    /**
     * 接口4：新运单、运输中、已完成运单统计
     * @return
     */
    List<CensusDTO> wayBillCensus();

    /**
     * 接口5：统计企业总数、车辆总数、人员总数
     * @return
     */
    List<CensusDTO> evpCensus();

    /**
     * 接口6：押运员或司机的运单个数排名
     * @return
     */
    List<CensusDTO> driverCensus();

    /**
     * 接口8：企业分类占比
     * @return
     */
    List<CensusDTO> componySortCensus();

    /**
     * 接口7：已托运、未托运、已完成托运单统计
     * @return
     */
    List<CensusDTO> orderCensus();

    /**
     * 接口1：统计长期未填报运单的车辆
     * @return
     */
    List<CensusDTO> vehicleCensus();

    /**
     * 前四大运输公司运输占比
     * @return
     */
    List<CensusDTO> fourGreatOwner();

    /**
     * 本市企业发货与收货运单数占比
     * @return
     */
    List<CensusDTO> shipReceivePercent();

    /**
     * 过去8天运单数统计
     * @return
     */
    List<CensusDTO> eightOrders();
}
