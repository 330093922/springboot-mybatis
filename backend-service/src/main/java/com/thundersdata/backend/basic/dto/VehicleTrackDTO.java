package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname VehicleTrackDTO
 * @Description TODO
 * @Date 2020/2/3 16:41
 * @Created wrc
 */
@Data
@ApiModel(value = "VehicleTrackDTO" ,description = "车辆行驶轨迹")
@Accessors(chain = true)
public class VehicleTrackDTO {

    @ApiModelProperty(value = "车辆id")
    private  String vehicleId;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;

}
