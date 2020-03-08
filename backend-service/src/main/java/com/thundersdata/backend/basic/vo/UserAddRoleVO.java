package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("用户绑定角色")
@Data
public class UserAddRoleVO {

    @ApiModelProperty(value = "人员id")
    private Integer personId;

    @ApiModelProperty(value = "角色id列表")
    private List<Integer> dataList;

}
