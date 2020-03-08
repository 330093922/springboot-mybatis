package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname lineDetailsDTO
 * @Description 根据运单id查询线路详情
 * @Date 2019/12/17 14:53
 * @Created wrc
 */
@ApiModel("根据运单id查询线路详情")
@Data
public class lineDetailsDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "主键ID" )
    private Integer id;


    /**
     * 线路名称
     */
    @ApiModelProperty(value = "线路名称")
    private String name;

    /**
     * 始发地
     */
    @ApiModelProperty(value = "始发地")
    private String startAddr;

    /**
     * 途径
     */
    @ApiModelProperty(value = "途径")
    private String way;

    /**
     * 里程
     */
    @ApiModelProperty(value = "里程",example = "100")
    private String km;

    /**
     * 终点站
     */
    @ApiModelProperty(value = "终点站")
    private String stopAddr;

}
