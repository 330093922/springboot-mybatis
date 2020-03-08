package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:auth_role_data表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-19
 */
@Data
public class AuthRoleData {
    /**
     * 
     */
    private Integer id;

    /**
     * 数据范围 0：自定义 1：全部
     */
    private Integer scope;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 数据类型 0:企业数据
     */
    private Integer dataType;

    /**
     * 数据id
     */
    private Integer dataId;

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
    private Boolean deleted;
}