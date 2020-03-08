package com.thundersdata.backend.basic.controller;

import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.AuthService;
import com.thundersdata.backend.basic.vo.LoginVo;
import com.thundersdata.backend.basic.vo.ModifyPasswordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "用户授权接口")
@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    /**
     * 用户登录方法
     * {
     * "oldPassword":"123123",
     * "newPassword":"525252"
     * }
     *
     * @param loginVo
     * @return
     */
    @ApiOperation(value = "用户登录方法", notes = "通过用户名,密码,返回用户信息")
    @PostMapping("login")
    public WaybillUser login(@RequestBody LoginVo loginVo) {
        WaybillUser loginUser = authService.login(loginVo.getUsername(), loginVo.getPassword());
        loginUser.setPassword("");

        return loginUser;
    }

    /**
     * 根据token获取用户信息
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    @GetMapping("getUserByToken")
    public WaybillUser getUserByToken(@RequestParam(value = "token", required = true) String token) {

        return authService.getUserByToken(token);
    }


    /**
     * 修改密码方法
     *
     * @param modifyPasswordVo
     * @param userId
     * @return
     */
    @ApiOperation(value = "修改用户密码", notes = "通过userId,旧密码,新密码修改用户密码")
    @PostMapping("modifyPassword")
    public String modifyPassword(@RequestBody ModifyPasswordVo modifyPasswordVo, @ApiIgnore() Integer userId) {
        Assert.notNull(userId, "用户ID不能为空!");
        authService.modifyPassword(userId, modifyPasswordVo.getOldPassword(), modifyPasswordVo.getNewPassword());

        return "密码修改成功!";
    }

    /**
     * 重置用户密码
     *
     * @param personList
     * @return
     */
    @PostMapping("resetPassword")
    @ApiOperation(value = "重置密码", notes = "通过用户id重置密码")
    public String resetPassword(@RequestBody List<Integer> personList) {

        return authService.resetPassword(personList);
    }
}
