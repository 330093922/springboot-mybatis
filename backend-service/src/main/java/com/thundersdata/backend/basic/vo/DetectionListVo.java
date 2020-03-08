package com.thundersdata.backend.basic.vo;

import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("自检内容管理")
@Data
public class DetectionListVo extends PageVo {
    @ApiModelProperty(value = "检查类型")
    private String type;

    @ApiModelProperty(value = "检查项")
    private String project;

    @ApiModelProperty(value = "内容")
    private String content;
}
