package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_detection表的实体类
 * @version
 * @author:  zzl
 * @创建时间: 2019-12-13
 */
@Data
public class WaybillDetectionDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 检查类型 
     */
    @ApiModelProperty(value = "检查类型")
    private String type;

    /**
     * 检查时间
     */
    @ApiModelProperty(value = "检查时间")
    private String time;

    /**
     * 检查项
     */
    @ApiModelProperty(value = "检查项")
    private String project;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;
    /**
     * 检查内容
     */
    @ApiModelProperty(value = "检查内容")
    private String content;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

 }