package com.thundersdata.backend.basic.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("电子运单BLOB字段")
@Data
public class WaybillOrderWithBLOBs extends WaybillOrder {
    /**
     * 注意事项
     */
    @ApiModelProperty(value = "注意事项")
    private String attention;

    /**
     * 应急处置措施
     */
    @ApiModelProperty(value = "应急处置措施")
    private String measures;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}