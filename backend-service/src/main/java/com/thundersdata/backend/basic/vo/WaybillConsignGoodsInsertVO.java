package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 托运单插入VO对象
 * auth wanghaibo
 */
@ApiModel("托运单插入VO对象")
@Data
public class WaybillConsignGoodsInsertVO {
    @ApiModelProperty(value = "货物id")
    private Integer goodsId;

    @ApiModelProperty(value = "总数量")
    private Integer totalNo;

    @ApiModelProperty(value = "备注")
    private String remark;
}
