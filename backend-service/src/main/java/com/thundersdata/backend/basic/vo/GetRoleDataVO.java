package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询角色数据权限")
@Data
public class GetRoleDataVO {

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "数据类型")
    private Integer dataType;
}
