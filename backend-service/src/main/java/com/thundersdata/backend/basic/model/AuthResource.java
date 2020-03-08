package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:auth_resource表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-19
 */
@Data
public class AuthResource {
    /**
     * 
     */
    private Integer id;

    /**
     * 资源唯一标识
     */
    private String resourceKey;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 排序优先级
     */
    private Integer orderValue;

    /**
     * 资源对应的url 资源或者后台接口
     */
    private String apiUrl;

    /**
     * 对应的前端图标属性
     */
    private String iconUrl;

    /**
     * 0菜单 1按钮等资源
     */
    private Integer type;

    /**
     * 资源描述
     */
    private String description;

    /**
     * 父级别id
     */
    private Integer parentId;

    /**
     * 业务字段
     */
    private String business;

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