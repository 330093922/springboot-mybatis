package com.thundersdata.backend.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PageVo {

    @ApiModelProperty(value = "页码")
    private Integer page = 0;

    @ApiModelProperty(value = "数量")
    private Integer pageSize = 10;
}
