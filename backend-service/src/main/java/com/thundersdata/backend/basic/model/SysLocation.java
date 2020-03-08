package com.thundersdata.backend.basic.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述:sys_location表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2020-01-22
 */
@Data
@ApiModel("地区表")
public class SysLocation {
    /**
     * 
     */
    @ApiModelProperty(value = "id",required = true)
    private Integer id;

    /**
     * 地区编号
     */
    @ApiModelProperty(value = "地区编号",required = true)
    private String locationCode;

    /**
     * 地区名称
     */
    @ApiModelProperty(value = "地区名称",required = true)
    private String locationName;

    /**
     * 地区级别
     */
    @ApiModelProperty(value = "地区名称",required = true)
    private Byte locationLevel;

    /**
     * 父级地区CODE
     */
    @ApiModelProperty(value = "父级地区CODE",required = true)
    private String parentCode;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度",required = true)
    private String lon;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度",required = true)
    private String lat;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",required = true)
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",required = true)
    private Date updatedAt;

    /**
     * 删除标记
     */
    private Byte isDeleted;
}