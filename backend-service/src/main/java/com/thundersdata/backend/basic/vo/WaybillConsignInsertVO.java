package com.thundersdata.backend.basic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 托运单新增VO对象
 * auth wanghaibo
 */
@ApiModel("托运单新增VO对象")
@Data
public class WaybillConsignInsertVO {


    /**
     * 发货企业id（托运人）
     */
    @ApiModelProperty(value = "发货企业id（托运人）")
    private Integer shipperId;

    /**
     * 运输企业id（承运人)
     */
    @ApiModelProperty(value = "运输企业id（承运人)")
    private Integer shipmentId;

    /**
     * 收货企业id (收货人)
     */
    @ApiModelProperty(value = "收货企业id (收货人)")
    private Integer receivingId;

    /**
     * 装货企业id（装货人）
     */
    @ApiModelProperty(value = "装货企业id（装货人）")
    private Integer expediterId;

    /**
     * 始发地（装货地点）
     */
    @ApiModelProperty(value = "始发地（装货地点）")
    private String shipmentStart;

    /**
     * 目的地（卸货地点）
     */
    @ApiModelProperty(value = "目的地（卸货地点）")
    private String shipmentStop;

    /**
     * 预计装货时间
     */
    @ApiModelProperty(value = "预计装货时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date shipmentTime;

    /**
     * 预计到达日期
     */
    @ApiModelProperty(value = "预计到达日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date shipmentArrive;

    /**
     * 应急联系电话
     */
    @ApiModelProperty(value = "应急联系电话")
    private String emergencyPhone;


    /**
     * 是否城市配送(0:否  1：是)
     */
    @ApiModelProperty(value = "城市配送")
    private Boolean isCity;

    /**
     * 注意事项
     */
    @ApiModelProperty(value = "注意事项")
    private String attention;

    /**
     * 应急处置措施（急救措施）
     */
    @ApiModelProperty(value = "应急处置措施（急救措施）")
    private String measures;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "托运单关联货物信息")
    List<WaybillConsignGoodsInsertVO> listConsignGoods;
}
