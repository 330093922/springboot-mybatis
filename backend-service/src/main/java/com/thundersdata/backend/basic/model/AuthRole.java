package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:auth_role表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-19
 */
@Data
public class AuthRole {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色数据范围：0自定义  1：全部
     */
    private Integer scope;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sn;

    /**
     * 是否能删除 0否  1是
     */
    private Byte isAdmin;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 删除标记
     */
    private Byte deleted;

    //角色id
    private Integer roleId;

}