package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.AuthResourceDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.dto.AuthRoleDataDTO;
import com.thundersdata.backend.basic.dto.PermissionDataDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.service.AuthorityService;
import com.thundersdata.backend.basic.vo.GetRoleDataVO;
import com.thundersdata.backend.basic.vo.RoleAddDataVO;
import com.thundersdata.backend.basic.vo.RoleAddMenuVO;
import com.thundersdata.backend.basic.vo.UserAddRoleVO;
import com.thundersdata.backend.common.model.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户权限接口")
@RestController
@RequestMapping("authority")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;


    /**
     * 查询角色菜单树
     *
     * @param roleId
     * @return
     */
    @ApiOperation(value = "查询角色菜单树", notes = "通过角色id查询菜单树")
    @GetMapping("getRoleMenu")
    public List<AuthResourceDTO> getRoleMenu(Integer roleId) {

        return authorityService.getRoleMenu(roleId);
    }

    /**
     * 角色绑定菜单权限
     */
    @ApiOperation(value = "角色绑定菜单权限", notes = "通过角色id绑定菜单树")
    @PostMapping("roleAddMenu")
    public Integer roleAddMenu(@RequestBody RoleAddMenuVO roleAddMenuVO) {

        return authorityService.roleAddMenu(roleAddMenuVO);
    }

    /**
     * 查询角色数据范围
     *
     * @param getRoleDataVO
     * @return
     */
    @ApiOperation(value = "查询角色数据范围", notes = "通过角色id查询数据范围")
    @PostMapping("getRoleData")
    public List<AuthRoleDataDTO> getRoleData(@RequestBody GetRoleDataVO getRoleDataVO) {

        return authorityService.getRoleData(getRoleDataVO.getRoleId(), getRoleDataVO.getDataType());
    }

    /**
     * 角色绑定数据权限
     */
    @ApiOperation(value = "角色绑定数据权限", notes = "通过角色id绑定数据权限")
    @PostMapping("roleAddData")
    public Integer roleAddData(@RequestBody RoleAddDataVO roleAddDataVO) {

        return authorityService.roleAddData(roleAddDataVO);
    }

    /**
     * 用户绑定角色
     */
    @ApiOperation(value = "用户绑定角色", notes = "通过用户id 批量绑定角色 用户绑定角色")
    @PostMapping("userAddRole")
    public Integer userAddRole(@RequestBody UserAddRoleVO userAddRoleVO) {

        return authorityService.userAddRole(userAddRoleVO);
    }

    /**
     * 查询用户所有角色
     *
     * @param personId 人员id
     * @return
     */
    @ApiOperation(value = "查询用户所有角色", notes = "通过用户id 查询用户所有角色")
    @GetMapping("getUserRole")
    public List<AuthRoleDTO> getUserRole(Integer personId) {

        return authorityService.getUserRole(personId);
    }

    /**
     * 查询用户所有权限
     *
     * @param userId 用户id
     * @return
     */
    @ApiOperation(value = "查询用户所有权限", notes = "通过token中的userId 查询当前用户的所有权限")
    @GetMapping("getUserRoleDetail")
    public List<AuthResourceDTO> getUserRoleDetail(Integer userId) {

        return authorityService.getUserRoleDetail(userId);
    }

    /**
     * 获取用户接口权限
     *
     * @param apiUrl 接口url
     * @param userId 用户id
     */
    @GetMapping("getPermissionByUrl")
    public ResponseData getPermissionByUrl(String apiUrl,
                                           Integer userId) {

        return authorityService.getPermissionByUrl(apiUrl, userId);
    }

    /**
     * 获取用户数据权限范围
     *
     * @param userId 用户id
     */
    @GetMapping("getPermissionData")
    public PermissionDataDTO getPermissionData(Integer userId) {

        return authorityService.getPermissionData(userId);
    }
}
