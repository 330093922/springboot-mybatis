package com.thundersdata.backend.basic.vo;

import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:waybill_vehicle表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@Data
@ApiModel("车辆查询")
public class WaybillVehicleVO extends PageVo {
    /**
     * 车辆id identity (1,1) not null 
     */
    private Integer id;


    /**
     * 车辆号码 
     */
    @ApiModelProperty(value = "车牌号码", required = true)
    private String vehicleCode;


    /**
     * 车辆类型（牵引车，挂车） 1:中型普通货车  2:保温冷藏车  3:其他车辆  4:危险品运输车  5:小型普通货车  6:平板车  7:挂车  8:牵引车  9:罐车  10:重型普通货车  11:集装箱车
     */
    @ApiModelProperty(value = "车辆类型", required = true)
    private Integer vehicleType;

    /**
     * 营运状态 0:营运;1:停运;2:报废;3:停驶;4:其他
     */
    @ApiModelProperty(value = "营运状态", required = true)
    private Integer vehicleStatus;

}