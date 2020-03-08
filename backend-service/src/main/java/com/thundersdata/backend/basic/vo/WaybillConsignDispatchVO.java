package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 托运单调度vo对象
 * auth wanghaibo
 */
@ApiModel("托运单调度VO对象")
@Data
public class WaybillConsignDispatchVO {

    /**
     * 托运单id
     */
    @ApiModelProperty(value = "托运单id")
    private Integer consignId;


    /**
     * 车辆id
     */
    @ApiModelProperty(value = "车辆id")
    private Integer vehicleId;

    /**
     * 挂车id
     */
    @ApiModelProperty(value = "挂车id")
    private Integer trailerId;

    /**
     * 驾驶员用户id
     */
    @ApiModelProperty(value = "驾驶员用户id")
    private Integer pilotId;

    /**
     * 押运员用户id
     */
    @ApiModelProperty(value = "押运员用户id")
    private Integer supercargoId;

    /**
     * 线路id
     */
    @ApiModelProperty(value = "线路id")
    private Integer routeId;


    @ApiModelProperty(value = "派发货物列表")
    private List<WaybillConsignGoodsDispatchVO> listGoods;

}
