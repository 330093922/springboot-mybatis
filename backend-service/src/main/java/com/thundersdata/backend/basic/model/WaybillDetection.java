package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_detection表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@Data
public class WaybillDetection {
    /**
     * id
     */
    private Integer id;

    /**
     * 检查类型 
     */
    private String type;

    /**
     * 检查时间
     */
    private String time;

    /**
     * 检查项
     */
    private String project;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

 }