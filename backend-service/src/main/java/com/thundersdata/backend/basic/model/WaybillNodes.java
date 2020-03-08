package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_nodes表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@Data
public class WaybillNodes {
    /**
     * 节点id
     */
    private Integer id;

    /**
     * 节点名称 
     */
    private String name;

    /**
     * 备用字段
     */
    private String field;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;
}