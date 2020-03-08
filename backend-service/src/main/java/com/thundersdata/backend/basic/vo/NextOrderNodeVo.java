package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("下一步订单")
public class NextOrderNodeVo {

    @ApiModelProperty(value = "订单id")
    private Integer orderId;

    @ApiModelProperty(value = "当前节点id")
    private Integer nodeId;

    @ApiModelProperty(value = "节点存储的内容")
    private String content;
}
