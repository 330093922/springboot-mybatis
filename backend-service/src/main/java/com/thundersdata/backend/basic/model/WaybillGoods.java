package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:waybill_goods表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@Data
public class WaybillGoods {
    /**
     * id
     */
    private Integer id;

    /**
     * 联合国货物编号 
     */
    private String unNo;

    /**
     * 货物名称
     */
    private String name;

    /**
     * 类/项别
     */
    private String type;

    /**
     * 包装类别
     */
    private String packagingType;

    /**
     * 别名
     */
    private String alias;

    /**
     * 密度（吨/立方）
     */
    private BigDecimal number;

    /**
     * 0:激活 1:禁止
     */
    private Byte status;

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