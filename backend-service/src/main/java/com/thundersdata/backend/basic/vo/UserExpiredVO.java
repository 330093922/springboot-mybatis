package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname UserExpiredVO
 * @Description TODO
 * @Date 2020/1/22 15:36
 * @Created wrc
 */
@ApiModel(value = "UserExpiredVO",description = "预警实体类信息")
@Data
public class UserExpiredVO {

    /**
     * 人员编号 identity (1,1) not null
     */
    @ApiModelProperty(value = "人员编号")
    private Integer id;

    /**
     * 所属单位id
     */
    @ApiModelProperty(value = "所属单位id")
    private String ownerId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名，企业名称，车牌号，运单号")
    private String name;


    @ApiModelProperty(value = "过期时间")
    private String employedEndDate;

}
