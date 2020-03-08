package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:auth_role_resource表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-19
 */
@Data
public class AuthRoleResource {
    /**
     * 
     */
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 资源id
     */
    private Integer resourceId;

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