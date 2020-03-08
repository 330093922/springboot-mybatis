package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;


/**
 * 人员下拉列表
 */
@ApiModel("人员下拉框")
@Data
public class WayBillUserDictDto {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String phone;

    /**
     * 从业类型 0:装卸管理员 1:特种设备作业人员 2:驾驶员 3:押运员 4:其他人员
     */
    @ApiModelProperty(value = "从业类型")
    private Integer type;
    /**
     *证件类型
     */
    @ApiModelProperty(value = "证件类型")
    private String  cardType;
    /**
     * 证件号
     */
    @ApiModelProperty(value = "证件号码")
    private String idCard;

}
