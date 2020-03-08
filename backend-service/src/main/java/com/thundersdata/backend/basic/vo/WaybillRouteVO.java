package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:waybill_route表的实体类
 * @version
 * @author:  gzw
 * @创建时间: 2019-12-16
 */
@Data
@ApiModel("运输路线管理")
public class WaybillRouteVO {

    /**
     * 线路名称
     */
    @ApiModelProperty(value = "线路名称", required = true)
    private String name;

    /**
     * 途径
     */
    @ApiModelProperty(value = "途径", required = true)
    private String way;

    /**
     * 始发地
     */
    @ApiModelProperty(value = "始发地", required = true)
    private String startAddr;

    /**
     * 终点站
     */
    @ApiModelProperty(value = "终点站", required = true)
    private String stopAddr;
}
