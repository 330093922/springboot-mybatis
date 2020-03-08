package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 角色,vo对象
 * </p>
 *
 * @author wanghaibo
 * @since 2019-12-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthRoleVO", description = "角色信息")
public class AuthRoleVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "排序")
    private Integer sn;

}
