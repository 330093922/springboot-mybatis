package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:waybill_detection表的实体类
 * @version
 * @author:  zzl
 * @创建时间: 2019-12-13
 */
@Data
public class WaybillDetectionDictDTO {
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
     * 检查内容
     */
    @ApiModelProperty(value = "检查内容")
    private String content;

 }