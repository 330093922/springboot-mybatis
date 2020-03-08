package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname DistriButeVO
 * @Description TODO
 * @Date 2019/12/8 11:31
 * @Created wrc
 */
@ApiModel("运单派发")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistriButeVo {

    @ApiModelProperty(value = "id")
    private  Integer id;

    @ApiModelProperty(value = "驾驶员id")
    private Integer pilotId;

    @ApiModelProperty(value = "押运员id")
    private Integer supercargoId;

    @ApiModelProperty(value = "车辆id")
    private Integer vehicleId;

    @ApiModelProperty(value = "挂车id")
    private Integer trailerId;

    //调度人
    private Integer schedulingUser;

    //调度时间
    private String schedulingDate;
}
