package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.model.WaybillFiles;

public interface WaybillFilesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillFiles record);

    int insertSelective(WaybillFiles record);

    WaybillFiles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillFiles record);

    int updateByPrimaryKey(WaybillFiles record);
}