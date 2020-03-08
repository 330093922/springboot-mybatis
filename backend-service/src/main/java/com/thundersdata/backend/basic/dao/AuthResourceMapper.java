package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.model.AuthResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthResource record);

    int insertSelective(AuthResource record);

    AuthResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthResource record);

    int updateByPrimaryKey(AuthResource record);


    /**
     * id 列表查询资源
     *
     * @param resourceList
     * @return
     */
    List<AuthResourceDTO> selectByIdList(@Param("resourceList") List<Integer> resourceList);


    /**
     * 查询所有资源
     *
     * @return
     */
    List<AuthResourceDTO> selectByMap();
}