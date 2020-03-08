package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:waybill_vehicle_gps表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2020-02-01
 */
@Data
public class WaybillVehicleGps {
    /**
     * id
     */
    private Integer id;

    /**
     * 车辆id
     */
    private String vehicleId;

    /**
     * 经度
     */
    private BigDecimal lon;

    /**
     * 纬度
     */
    private BigDecimal lat;

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