package com.thundersdata.backend.basic.dao;


import com.thundersdata.backend.basic.dto.AuthRoleDataDTO;
import com.thundersdata.backend.basic.dto.PermissionDataDTO;
import com.thundersdata.backend.basic.model.AuthRoleData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthRoleDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthRoleData record);

    int insertSelective(AuthRoleData record);

    AuthRoleData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthRoleData record);

    int updateByPrimaryKey(AuthRoleData record);

    /**
     * 查询角色数据权限范围
     *
     * @param roleId
     * @return
     */
    List<AuthRoleDataDTO> selectByRole(@Param("roleId") Integer roleId,
                                       @Param("dataType") Integer dataType);

    /**
     * 插入角色数据范围
     *
     * @param roleId
     * @param dataType
     * @param dataList
     * @return
     */
    Integer replaceIntoByRole(@Param("roleId") Integer roleId,
                              @Param("dataType") Integer dataType,
                              @Param("dataList") List<Integer> dataList);

    /**
     * 删除角色数据范围
     *
     * @param roleId
     * @return
     */
    Integer deleteByRole(@Param("roleId") Integer roleId);

    /**
     * 通过user查询 数据权限
     *
     * @param userId
     * @return
     */
    List<Integer> selectByUser(@Param("isUni") Boolean isUni,
                               @Param("userId") Integer userId,
                               @Param("roleIds") List<Integer> roleIds);


    /**
     * 批量插入
     *
     * @param authRoleData
     * @return
     */
    int BatchInsertion(List<AuthRoleData> authRoleData);

}