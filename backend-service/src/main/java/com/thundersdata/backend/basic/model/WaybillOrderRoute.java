package com.thundersdata.backend.basic.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 描述:waybill_order_route表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-17
 */
@Data
@Accessors(chain = true)
public class WaybillOrderRoute {
    /**
     * id
     */
    private Integer id;

    /**
     * 运单id
     */
    private Integer orderId;

    /**
     * 线路id
     */
    private Integer routeId;

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