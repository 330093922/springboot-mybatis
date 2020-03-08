package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.dto.*;
import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDataDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.vo.RoleAddDataVO;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import com.thundersdata.backend.basic.vo.UserAddRoleVO;
import com.thundersdata.backend.common.model.ResponseData;

import java.util.List;


/**
 * @author paris
 */
public interface AuthorityService {

    /**
     * 查询角色菜单树
     *
     * @param roleId
     * @return
     */
    List<AuthResourceDTO> getRoleMenu(Integer roleId);

    /**
     * 角色绑定菜单权限
     */
    Integer roleAddMenu(RoleAddMenuVO roleAddMenuVO);

    /**
     * 查询角色数据范围
     *
     * @param roleId
     * @return
     */
    List<AuthRoleDataDTO> getRoleData(Integer roleId, Integer dataType);

    /**
     * 角色绑定数据权限
     */
    Integer roleAddData(RoleAddDataVO roleAddDataVO);

    /**
     * 用户绑定角色
     */
    Integer userAddRole(UserAddRoleVO userAddRoleVO);

    /**
     * 查询用户所有角色
     *
     * @param personId 人员id
     * @return
     */
    List<AuthRoleDTO> getUserRole(Integer personId);

    /**
     * 查询用户所权限
     *
     * @param userId 用户id
     * @return
     */
    List<AuthResourceDTO> getUserRoleDetail(Integer userId);

    /**
     * 获取接口权限
     *
     * @param apiUrl 接口url
     * @param userId 用户id
     */
    ResponseData getPermissionByUrl(String apiUrl,
                                    Integer userId);

    /**
     * 获取接口权限
     *
     * @param userId 用户id
     */
    PermissionDataDTO getPermissionData(Integer userId);
}
