package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 角色,DTO对象
 * </p>
 *
 * @author wanghaibo
 * @since 2019-12-19
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthRoleDTO", description = "角色信息")
public class AuthRoleDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @ApiModelProperty(value = "角色数据范围：0自定义  1：全部")
    private Integer scope;

    @ApiModelProperty(value = "排序")
    private Integer sn;

    @ApiModelProperty(value = "是否能删除 0否  1是")
    private Byte isAdmin;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "删除标记")
    private Byte deleted;
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * id
     */
    private Integer userId;

}

