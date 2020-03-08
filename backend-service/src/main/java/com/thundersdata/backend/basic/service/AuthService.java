package com.thundersdata.backend.basic.service;


import com.thundersdata.backend.basic.model.WaybillUser;

import java.util.List;

/**
 * @author paris
 */
public interface AuthService {


    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    WaybillUser login(String username, String password);


    /**
     * 根据用户id修改密码方法
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Boolean modifyPassword(Integer id, String oldPassword, String newPassword);

    /**
     * 通过token获取user信息
     *
     * @param token token
     * @return
     */
    WaybillUser getUserByToken(String token);


    /**
     * 重置用户密码
     *
     * @param personList
     * @return
     */
    String resetPassword(List<Integer> personList);

}
