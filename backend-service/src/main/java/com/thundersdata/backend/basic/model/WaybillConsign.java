package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_consign表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2020-02-01
 */
@Data
public class WaybillConsign {
    /**
     * id
     */
    private Integer id;

    /**
     * 托运单编号
     */
    private String consignCode;

    /**
     * 发货企业id（托运人）
     */
    private Integer shipperId;

    /**
     * 运输企业id（承运人)
     */
    private Integer shipmentId;

    /**
     * 收货企业id (收货人)
     */
    private Integer receivingId;

    /**
     * 装货企业id（装货人）
     */
    private Integer expediterId;

    /**
     * 始发地（装货地点）
     */
    private String shipmentStart;

    /**
     * 目的地（卸货地点）
     */
    private String shipmentStop;

    /**
     * 预计装货时间
     */
    private Date shipmentTime;

    /**
     * 预计到达日期
     */
    private Date shipmentArrive;

    /**
     * 应急联系电话
     */
    private String emergencyPhone;

    /**
     * 运单状态  0:新建　1:已接单 2:调度中 3:已拒绝 4:已承运
     */
    private Integer status;

    /**
     * 0:否  1：是
     */
    private Boolean isCity;

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