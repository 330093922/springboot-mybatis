package com.thundersdata.backend.basic.dao;

import com.thundersdata.backend.basic.dto.SysLocationDTO;
import com.thundersdata.backend.basic.model.SysLocation;

import java.util.List;

public interface SysLocationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLocation record);

    int insertSelective(SysLocation record);

    SysLocation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLocation record);

    int updateByPrimaryKey(SysLocation record);

    /**
     * 查询所有地市信息
     * @return
     */
    List<SysLocationDTO> getCityList();
}