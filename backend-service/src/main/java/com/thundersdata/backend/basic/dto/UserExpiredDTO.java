package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname UserExpiredDTO
 * @Description TODO 过期人员实体类
 * @Date 2020/1/22 14:50
 * @Created wrc
 */
@Data
@ApiModel(value = "UserExpiredDTO" ,description = "预警实体类")
@Accessors(chain = true)
public class UserExpiredDTO {

    @ApiModelProperty(value = "省")
    private  String province;

    @ApiModelProperty(value = "城市")
    private  String city;

    @ApiModelProperty(value = "区县")
    private  String county;

    @ApiModelProperty(value = "开始时间")
    private String startDate;

    @ApiModelProperty(value = "结束时间")
    private String endDate;


}
