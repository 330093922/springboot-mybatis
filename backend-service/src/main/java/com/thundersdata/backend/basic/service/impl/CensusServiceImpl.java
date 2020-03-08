package com.thundersdata.backend.basic.service.impl;

import com.thundersdata.backend.basic.dao.CensusMapper;
import com.thundersdata.backend.basic.dto.CensusDTO;
import com.thundersdata.backend.basic.enums.OwnerTypeEnum;
import com.thundersdata.backend.basic.service.CensusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CensusServiceImpl implements CensusService {
    @Autowired
    private CensusMapper censusMapper;

    /**
     * 接口4：新运单、运输中、已完成运单统计
     * @return
     */
    @Override
    public List<CensusDTO> wayBillCensus() {
        return censusMapper.wayBillCensus();
    }

    /**
     * 接口5：统计企业总数、车辆总数、人员总数
     * @return
     */
    @Override
    public List<CensusDTO> evpCensus() {
        return censusMapper.evpCensus();
    }

    /**
     * 接口6：押运员或司机的运单个数排名
     * @return
     */
    @Override
    public List<CensusDTO> driverCensus() {
        return censusMapper.driverCensus();
    }

    /**
     * 接口8：企业分类占比
     * @return
     */
    @Override
    public List<CensusDTO> componySortCensus() {
        List<CensusDTO> list_ = censusMapper.componySortCensus();
        //1. 填充缺失的ownerType
        List<CensusDTO> list_All = new ArrayList<CensusDTO>();
        for(int i=1; i<7; ++i){
            CensusDTO censusDTO_ = new CensusDTO();
            censusDTO_.setCountNum(0);
            censusDTO_.setOwnerType(i);
            list_All.add(censusDTO_);
        }
        for(int j=0; j<list_All.size(); ++j){
            for(int b=0; b<list_.size(); ++b){
                if(list_.get(b).getOwnerType() == list_All.get(j).getOwnerType()){
                    list_All.get(j).setCountNum(list_.get(b).getCountNum());
                }
            }

            //2. 枚举类替换数字为中文解释
            list_All.get(j).setOwnerTypeCn(OwnerTypeEnum.find(list_All.get(j).getOwnerType()));
        }
        return list_All;
    }

    /**
     * 接口7：已托运、未托运、已完成托运单统计
     * @return
     */
    @Override
    public List<CensusDTO> orderCensus() {
        return censusMapper.orderCensus();
    }

    /**
     * 接口1：统计长期未填报运单的车辆
     * @return
     */
    @Override
    public List<CensusDTO> vehicleCensus() {
        return censusMapper.vehicleCensus();
    }

    /**
     * 前四大运输公司运输占比
     * @return
     */
    @Override
    public List<CensusDTO> fourGreatOwner() {
        return censusMapper.fourGreatOwner();
    }

    /**
     * 市企业发货与收货运单数占比
     * @return
     */
    @Override
    public List<CensusDTO> shipReceivePercent() {
        return censusMapper.shipReceivePercent();
    }

    /**
     * 过去8天运单数统计
     * @return
     */
    @Override
    public List<CensusDTO> eightOrders() {
        return censusMapper.eightOrders();
    }
}
