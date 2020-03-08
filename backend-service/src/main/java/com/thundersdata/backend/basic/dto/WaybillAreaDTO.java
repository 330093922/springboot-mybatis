package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Classname WaybillAreaDTO
 * @Description 下拉框返回实体类
 * @Date 2019/12/17 14:36
 * @Created wrc
 */
@ApiModel("装卸区域下拉框")
@Data
public class WaybillAreaDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;


    //企业id
    @ApiModelProperty(value = "企业id")
    private Integer ownerId;

    /**
     * 装卸区域编号
     */
    @ApiModelProperty(value = "装卸区域编号")
    private String areaNo;

    /**
     * 装卸区域名称
     */
    @ApiModelProperty(value = "卸区域名称")
    private String areaName;

    /**
     * 装卸区类型：0:装卸货 1:装货 2:卸货
     */
    @ApiModelProperty(value = "卸区类型：0:装卸货 1:装货 2:卸货")
    private Byte type;

    /**
     * 可装卸货货物名称
     */
    @ApiModelProperty(value = "可装卸货货物名称")
    private String goodsName;

    /**
     * 危险特性分类
     */
    @ApiModelProperty(value = "危险特性分类")
    private String classify;

    /**
     * 投入日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "投入日期")
    private Date inputDate;

    /**
     * 容积（立方米）
     */
    @ApiModelProperty(value = "容积（立方米）",example = "1")
    private Integer volume;

    /**
     * 长（米）
     */
    @ApiModelProperty(value = "长（米）",example = "1")
    private Integer length;

    /**
     * 宽（米）
     */
    @ApiModelProperty(value = "宽（米）",example = "1")
    private Integer wide;

    /**
     * 装卸区域实景
     */
    @ApiModelProperty(value = "装卸区域实景")
    private String imageUrl;



}
