package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.common.utils.PageParam;
import org.apache.ibatis.annotations.Param;
import java.util.List;


public interface AuthRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRole record);

    int insertSelective(AuthRole record);

    AuthRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRole record);

    int updateByPrimaryKey(AuthRole record);

    int deleteAuthRoleBatch(@Param("ids") String ids);

    /**
     * 分页查询用户角色信息
     * @param page
     * @param roleName
     * @param description
     * @return
     */
    List<AuthRoleDTO> selectAuthRole(@Param("page") PageParam page, @Param("roleName") String roleName, @Param("description") String description);

    /**
     * 获取分页查询用户角色信息的条数
     * @param roleName
     * @param description
     * @return
     */
    int countAuthRole(@Param("roleName") String roleName, @Param("description") String description);

     /** 更新角色数据范围
     *
     * @param roleId
     * @param scope
     * @return
     */
    Integer updateScope(@Param("roleId") Integer roleId,
                        @Param("scope") Integer scope);
}