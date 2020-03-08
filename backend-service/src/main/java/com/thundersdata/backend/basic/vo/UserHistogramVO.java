package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname UserHistogramVO
 * @Description TODO
 * @Date 2020/2/7 10:33
 * @Created wrc
 */
@Data
@Accessors(chain = true)
@ApiModel("人员统计柱状图")
public class UserHistogramVO {

    @ApiModelProperty(value = "条数")
    private String count;

    @ApiModelProperty(value = "日期")
    private String employedEndDate;
}
