package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_consign_goods表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2020-02-01
 */
@Data
public class WaybillConsignGoods {
    /**
     * id
     */
    private Integer id;

    /**
     * 货物id
     */
    private String goodId;

    /**
     * 托运单id
     */
    private Integer consignId;

    /**
     * 剩余数量 剩余为0 不可操作
     */
    private Integer surplusNo;

    /**
     * 总计数量
     */
    private Integer totalNo;

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

    /**
     * 备注
     */
    private String remark;


}