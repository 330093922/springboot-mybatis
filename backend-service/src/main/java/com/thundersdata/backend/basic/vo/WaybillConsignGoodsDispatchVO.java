package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 托运单调度货物数量vo对象
 * auth wanghaibo
 */
@ApiModel("托运单调度货物数量VO对象")
@Data
public class WaybillConsignGoodsDispatchVO {


    /**
     * 货物id
     */
    @ApiModelProperty(value = "货物id")
    private Integer goodsId;

    @ApiModelProperty(value = "派发数量")
    private Integer dispatchNo;
}
