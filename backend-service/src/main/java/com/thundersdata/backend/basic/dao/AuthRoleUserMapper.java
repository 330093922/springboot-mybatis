package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.model.AuthRoleUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleUser record);

    int insertSelective(AuthRoleUser record);

    AuthRoleUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleUser record);

    int updateByPrimaryKey(AuthRoleUser record);

    /**
     * 用户绑定角色
     *
     * @param personId
     * @param roleList
     * @return
     */
    Integer replaceIntoByUser(@Param("personId") Integer personId,
                              @Param("roleList") List<Integer> roleList);

    /**
     * 删除用户绑定角色
     *
     * @param personId
     * @return
     */
    Integer deleteByUser(@Param("personId") Integer personId);

    /**
     * 查询用户拥有的所有角色
     *
     * @param personId
     * @return
     */
    List<AuthRoleDTO> selectByUser(@Param("personId") Integer personId);

    /**
     * 查询用户所有权限
     *
     * @param userId
     * @return
     */
    List<AuthResourceDTO> selectResourceByUser(@Param("userId") Integer userId);
}