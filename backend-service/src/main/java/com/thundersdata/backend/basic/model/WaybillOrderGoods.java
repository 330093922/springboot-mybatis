package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_order_goods表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@ApiModel("电子运单货物")
@Data
public class WaybillOrderGoods {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String orderCode;

    /**
     * 联合国统一编号
     */
    @ApiModelProperty(value = "联合国统一编号")
    private String goodsCode;

    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    private String goodsName;

    /**
     * 类别
     */
    @ApiModelProperty(value = "类别")
    private String goodsType;

    /**
     * 包装规格
     */
    @ApiModelProperty(value = "包装规格")
    private String goodsSize;

    /**
     * 密度
     */
    @ApiModelProperty(value = "密度")
    private String goodsWeight;

    /**
     * 运输数量
     */
    @ApiModelProperty(value = "运输数量")
    private Integer goodsVolume;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}