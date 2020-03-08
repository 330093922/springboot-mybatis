package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname exaMineVO
 * @Description TODO
 * @Date 2019/12/8 13:59
 * @Created wrc
 */
@ApiModel("运单审批")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExaMineVo {


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

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "节点内容")
    private String content;

    @ApiModelProperty(value = "运单编号")
    private String orderCode;

    /**
     * 节点id
     */
    private Integer nodeId;

    @ApiModelProperty(value = "运单状态  0:新建 1:已调度 2:运输中 3:待审核 4:已完成 5:已拒绝")
    private Integer status;



}
