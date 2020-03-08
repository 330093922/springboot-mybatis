package com.thundersdata.backend.basic.service.impl;

import cn.hutool.core.date.DateUtil;
import com.thundersdata.backend.basic.dao.WaybillUserMapper;
import com.thundersdata.backend.basic.model.WaybillUser;
import com.thundersdata.backend.basic.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_MS_PATTERN;

/**
 * @author paris
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private WaybillUserMapper userMapper;


    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public WaybillUser login(String username, String password) {
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        Map<String, String> param = new HashMap<>();
        param.put("username", username);
        param.put("password", pwd);
        WaybillUser user = userMapper.getWaybillUserByPassword(param);
        Assert.notNull(user, "账号或密码错误！");
        String token = user.getToken();
        if(StringUtils.isBlank(token)){
            token = getToken(username, pwd);
            user.setToken(token);
            userMapper.updateByPrimaryKey(user);
        }

        return user;
    }

    /**
     * 根据用户id修改密码方法
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public Boolean modifyPassword(Integer id, String oldPassword, String newPassword) {
        Assert.notNull(id, "用户id不能为空！");
        Assert.notNull(oldPassword, "密码不能为空！");
        Assert.notNull(newPassword, "新密码不能为空！");
        WaybillUser user = userMapper.selectByPrimaryKey(id);
        Assert.notNull(user, "用户不存在！");
        String oldPwd = DigestUtils.md5DigestAsHex(oldPassword.getBytes());
        if (!oldPwd.equals(user.getPassword())) {
            Assert.notNull(null, "旧密码输入错误！");
        }
        String newPwd = DigestUtils.md5DigestAsHex(newPassword.getBytes());
        user.setPassword(newPwd);
        userMapper.updateByPrimaryKey(user);
        return true;
    }

    /**
     * 通过token获取user信息
     *
     * @param token token
     * @return
     */
    @Override
    public WaybillUser getUserByToken(String token) {

        // TODO token过期
        // String outTime = DateUtil.now();

        return userMapper.getWaybillUserByToken(token);
    }

    /**
     * 重置用户密码
     *
     * @param personList
     * @return
     */
    @Override
    public String resetPassword(List<Integer> personList) {

        Assert.notNull(personList, "请选择用户！");
        userMapper.resetPassword(personList);

        return "重置成功！密码:123456!";
    }

    /**
     * 获取token
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    private String getToken(String username, String password) {

        String data = DateUtil.format(new Date(), PURE_DATETIME_MS_PATTERN);

        return DigestUtils.md5DigestAsHex((username + "access_token" + password + data).getBytes());
    }

}
