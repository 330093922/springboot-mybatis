package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.model.WaybillVehicleGps;

public interface WaybillVehicleGpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillVehicleGps record);

    int insertSelective(WaybillVehicleGps record);

    WaybillVehicleGps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillVehicleGps record);

    int updateByPrimaryKey(WaybillVehicleGps record);
}