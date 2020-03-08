package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("角色绑定数据权限")
@Data
public class RoleAddDataVO {
    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "数据id列表")
    private List<Integer> dataList;

    @ApiModelProperty(value = "数据范围 0：自定义 1：全部")
    private Integer scope;

    @ApiModelProperty(value = "数据类型 0:企业数据")
    private Integer dataType;

}
