package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.model.WaybillOrderVehicle;

public interface WaybillOrderVehicleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillOrderVehicle record);

    int insertSelective(WaybillOrderVehicle record);

    WaybillOrderVehicle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillOrderVehicle record);

    int updateByPrimaryKey(WaybillOrderVehicle record);


}