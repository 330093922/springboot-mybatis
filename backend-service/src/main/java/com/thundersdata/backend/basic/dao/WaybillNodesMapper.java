package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.model.WaybillNodes;

public interface WaybillNodesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaybillNodes record);

    int insertSelective(WaybillNodes record);

    WaybillNodes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaybillNodes record);

    int updateByPrimaryKey(WaybillNodes record);
}