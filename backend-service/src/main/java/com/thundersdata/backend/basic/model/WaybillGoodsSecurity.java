package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_goods_security表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@Data
public class WaybillGoodsSecurity {
    /**
     * id
     */
    private Integer id;

    /**
     * 货物id 
     */
    private Integer goodsId;

    /**
     * 安全图片1
     */
    private String imageOne;

    /**
     * 安全图片2
     */
    private String imageTow;

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