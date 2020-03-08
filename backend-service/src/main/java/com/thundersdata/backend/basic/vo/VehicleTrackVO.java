package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Classname VehicleTrackVO
 * @Description TODO
 * @Date 2020/2/3 16:51
 * @Created wrc
 */
@ApiModel("汽车轨迹")
@Data
public class VehicleTrackVO {

    @ApiModelProperty(value = "汽车id")
    private String vehicleId;

    @ApiModelProperty(value = "车牌号")
    private String vehicleCode;

    @ApiModelProperty(value = "经度")
    private String lon;

    @ApiModelProperty(value = "纬度")
    private String lat;

    @ApiModelProperty(value = "海拔高度")
    private String elevation;

    @ApiModelProperty(value = "车速")
    private String speed;

    @ApiModelProperty(value = "行驶方向")
    private String direction;

    @ApiModelProperty(value = "行驶时间")
    private String created_at;

}
