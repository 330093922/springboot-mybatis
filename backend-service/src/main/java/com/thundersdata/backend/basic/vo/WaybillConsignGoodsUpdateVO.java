package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 托运单货物更新VO
 * auth wanghaibo
 */
@ApiModel("托运单更新VO对象")
@Data
public class WaybillConsignGoodsUpdateVO {
    @ApiModelProperty(value = "货物id")
    private Integer goodsId;

    @ApiModelProperty(value = "总数量")
    private Integer totalNo;

    @ApiModelProperty(value = "剩余数量")
    private Integer surplusNo;

    @ApiModelProperty(value = "备注")
    private String remark;


}
