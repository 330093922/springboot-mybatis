package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.model.AuthResource;
import com.thundersdata.backend.basic.model.AuthRoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleResource record);

    int insertSelective(AuthRoleResource record);

    AuthRoleResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleResource record);

    int updateByPrimaryKey(AuthRoleResource record);

    /**
     * 查询所有资源列表
     *
     * @return
     */
    List<AuthResourceDTO> getMenuList();

    /**
     * 查询角色权限列表
     *
     * @param roleId
     * @return
     */
    List<Integer> selectByRole(@Param("roleId") Integer roleId);

    /**
     * 角色绑定资源权限
     *
     * @param roleId
     * @return
     */
    Integer replaceIntoByRole(@Param("roleId") Integer roleId,
                              @Param("menuList") List<Integer> menuList);

    /**
     * 删除角色绑定资源权限
     *
     * @param roleId
     * @return
     */
    Integer deleteByRole(@Param("roleId") Integer roleId);

    /**
     * 接口权限效验 查询
     *
     * @param userId
     * @param apiUrl
     * @return
     */
    List<AuthResource> getPermissionByUrlWithValue(@Param("userId") Integer userId,
                                                   @Param("apiUrl") String apiUrl);
}