package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.vo.AuthRoleVO;
import com.thundersdata.backend.common.utils.Pagination;

/**
 * 用户角色服务层接口
 * @author wanghaibo
 */
public interface AuthRoleService {


    /**
     * 新增用户角色信息
     * @param authRoleVO
     * @return
     */
    int insertAuthRole(AuthRoleVO authRoleVO);

    /**
     * 修改用户角色信息
     * @param authRoleVO
     * @return
     */
    int updateAuthRole(AuthRoleVO authRoleVO);

    /**
     * 根据主键查询用户角色信息
     * @param id
     * @return
     */
    AuthRoleDTO selectAuthRoleVO(Integer id);

    /**
     * 删除用户角色信息
     * @param id
     * @return
     */
    int deleteAuthRole(Integer id);

    /**
     * 批量删除用户角色信息
     * @param ids
     * @return
     */
    int deleteAuthRoleBatch(String ids);

    /**
     * 分页查询用户角色
     * @param page
     * @param pageSize
     * @param roleName
     * @param description
     * @return
     */
    Pagination<AuthRoleDTO> selectAuthRole(Integer page, Integer pageSize, String roleName, String description);
}
