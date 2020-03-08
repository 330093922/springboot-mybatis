package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname WaybillAreaVo
 * @Description TODO
 * @Date 2019/12/13 11:00
 * @Created wrc
 */
@ApiModel("装卸区域列表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaybillAreaVo {

    /**
     * 装卸区域编号
     */
    @ApiModelProperty(value = "装卸区域编号")
    private String areaNo;

    /**
     * 装卸区域名称
     */
    @ApiModelProperty(value = "装卸区域名称")
    private String areaName;

    /**
     * 可装卸货货物名称
     */
    @ApiModelProperty(value = "可装卸货货物名称")
    private String goodsName;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer page;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    //企业id
    private  Integer[] dataList;
    //是否开启
    private Boolean isScope;
}
