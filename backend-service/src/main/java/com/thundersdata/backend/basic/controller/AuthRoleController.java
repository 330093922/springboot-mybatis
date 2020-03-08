package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.dto.AuthRoleDTO;
import com.thundersdata.backend.basic.model.AuthRole;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.AuthRoleService;
import com.thundersdata.backend.basic.service.AuthService;
import com.thundersdata.backend.basic.vo.AuthRoleVO;
import com.thundersdata.backend.basic.vo.LoginVo;
import com.thundersdata.backend.basic.vo.ModifyPasswordVo;
import com.thundersdata.backend.common.utils.Pagination;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "角色接口")
@RestController
@RequestMapping("role")
public class AuthRoleController {
    @Autowired
    private AuthRoleService authRoleService;

    @ApiOperation(value = "添加角色", notes = "添加角色接口")
    @PostMapping("insert")
    public Integer insertAuthRole(@RequestBody AuthRoleVO authRole) {

        return authRoleService.insertAuthRole(authRole);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色接口")
    @PutMapping("update")
    public Integer updateAuthRole(@RequestBody AuthRoleVO authRole) {

        return authRoleService.updateAuthRole(authRole);
    }

    @ApiOperation(value = "查询角色信息", notes = "查询角色信息,根据角色id")
    @GetMapping("detail")
    @ResponseBody
    public AuthRoleDTO detail(Integer id) {
        return authRoleService.selectAuthRoleVO(id);
    }

    @ApiOperation(value = "删除角色信息", notes = "删除角色信息,根据角色id")
    @DeleteMapping("delete")
    @ResponseBody
    public Integer delete(Integer id) {
        return authRoleService.deleteAuthRole(id);
    }

    @ApiOperation(value = "批量删除角色信息", notes = "删除角色信息,根据角色id")
    @DeleteMapping("deleteBatch")
    @ResponseBody
    public Integer delete(String ids) {
        return authRoleService.deleteAuthRoleBatch(ids);
    }



    @ApiOperation(value = "查询角色列表", notes = "查询角色列表")
    @GetMapping("roleList")
    public Pagination<AuthRoleDTO> selectAuthRoleList(@RequestParam(name="page", defaultValue="1") Integer page,@RequestParam(name="pageSize", defaultValue="10") Integer pageSize, String roleName, String description) {

        return authRoleService.selectAuthRole(page, pageSize, roleName, description);
    }

}
