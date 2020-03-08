package com.thundersdata.backend.basic.dao;

import com.thundersdata.backend.basic.model.PortalNews;

public interface PortalNewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PortalNews record);

    int insertSelective(PortalNews record);

    PortalNews selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PortalNews record);

    int updateByPrimaryKeyWithBLOBs(PortalNews record);

    int updateByPrimaryKey(PortalNews record);
}