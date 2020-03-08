package com.thundersdata.backend.basic.vo;

import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("订单查询")
public class SelectOrderList extends PageVo {

    @ApiModelProperty(value = "订单号")
    private String orderCode;

    @ApiModelProperty(value = "司机姓名")
    private String pilot;

    @ApiModelProperty(value = "托运企业")
    private String owner;

    @ApiModelProperty(value = "状态列表", required = true)
    List<Integer> states;
}
