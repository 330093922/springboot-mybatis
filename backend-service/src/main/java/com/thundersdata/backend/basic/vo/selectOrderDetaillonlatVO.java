package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wrc
 * @Classname selectOrderDetaillonlatVO
 * @Description TODO
 * @Date 2020/2/20 13:33
 */
@Data
@ApiModel(value = "selectOrderDetaillonlatVO",description = "运单地图预警")
public class selectOrderDetaillonlatVO {

    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String ordercode;


    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private String lon;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private String lat;
    /**
     * 装货地点
     */
    @ApiModelProperty(value = "装货地点")
    private String shipmentstart;

    /**
     * 卸货地点
     */
    @ApiModelProperty(value = "卸货地点")
    private String shipmentstop;

    /**
     * 预计到达时间
     */
    @ApiModelProperty(value = "预计到达时间")
    private String shipmentarrive;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    private String vehiclecode;


    /**
     * 发货企业名称
     */
    @ApiModelProperty(value = "发货企业名称")
    private String shippername;

    /**
     * 收货企业名称
     */
    @ApiModelProperty(value = "收货企业名称")
    private String receivingname;

    /**
     * 装货企业名称
     */
    @ApiModelProperty(value = "装货企业名称")
    private String expeditername;


    /**
     * 运输企业名称
     */
    @ApiModelProperty(value = "运输企业名称")
    private String shipmentname;









}
