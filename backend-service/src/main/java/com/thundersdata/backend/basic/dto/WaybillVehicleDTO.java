package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:waybill_vehicle表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@ApiModel("车辆详情")
@Data
public class WaybillVehicleDTO {
    /**
     * 车辆id identity (1,1) not null 
     */
    @ApiModelProperty(value = "车辆id")
    private String id;

    /**
     * 所属单位id 
     */
    @ApiModelProperty(value = "所属单位id")
    private String ownerId;

    /**
     * 所属单位名称
     */
    @ApiModelProperty(value = "所属单位名称")
    private String ownerName;

    /**
     * 驾驶员
     */
    @ApiModelProperty(value = "驾驶员")
    private String pilot;

    /**
     * 押运员
     */
    @ApiModelProperty(value = "押运员")
    private String supercargo;

    /**
     * 所属行业
     */
    @ApiModelProperty(value = "所属行业")
    private String ownerType;

    /**
     * 车辆号码 
     */
    @ApiModelProperty(value = "车辆号码")
    private String vehicleCode;

    /**
     * 品牌型号
     */
    @ApiModelProperty(value = "品牌型号")
    private String vehicleModel;

    /**
     * 车辆类型（牵引车，挂车） 1:中型普通货车  2:保温冷藏车  3:其他车辆  4:危险品运输车  5:小型普通货车  6:平板车  7:挂车  8:牵引车  9:罐车  10:重型普通货车  11:集装箱车
     */
    @ApiModelProperty(value = "车辆类型（牵引车，挂车） 1:中型普通货车  2:保温冷藏车  3:其他车辆  4:危险品运输车  5:小型普通货车  6:平板车  7:挂车  8:牵引车  9:罐车  10:重型普通货车  11:集装箱车")
    private Integer vehicleType;

    /**
     * 营运状态 0:营运;1:停运;2:报废;3:停驶;4:其他
     */
    @ApiModelProperty(value = "营运状态 0:营运;1:停运;2:报废;3:停驶;4:其他")
    private Integer vehicleStatus;

    /**
     * 车牌颜色 jt/t 415规定
     */
    @ApiModelProperty(value = "车牌颜色 jt/t 415规定")
    private Integer color;

    /**
     * 经营范围 
     */
    @ApiModelProperty(value = "经营范围")
    private String manageArea;

    /**
     * 道路运输证号 
     */
    @ApiModelProperty(value = "道路运输证号")
    private String transportNumber;

    /**
     * 有效期起 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "有效期起")
    private Date firstDate;

    /**
     * 有效期至 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "有效期至")
    private Date expireDate;

    /**
     * 发证机关 
     */
    @ApiModelProperty(value = "发证机关")
    private String grantOrgan;

    /**
     * 核定载重量 
     */
    @ApiModelProperty(value = "核定载重量")
    private BigDecimal loadCapacity;

    /**
     * 总质量 
     */
    @ApiModelProperty(value = "总质量")
    private BigDecimal ton;

    /**
     * 罐体容积 
     */
    @ApiModelProperty(value = "罐体容积")
    private String volume;

    /**
     * 罐体编号
     */
    @ApiModelProperty(value = "罐体编号", required = true)
    private String tanksNo;

    /**
     * 是否运输毒物 0：不是  1：是
     */
    @ApiModelProperty(value = "是否运输毒物 0：不是  1：是")
    private Integer isToxic;

    /**
     * 是否运输强腐蚀 0：不是  1：是
     */
    @ApiModelProperty(value = "是否运输强腐蚀 0：不是  1：是")
    private Integer isEtch;

    /**
     * 是否运输爆炸品 0：不是  1：是
     */
    @ApiModelProperty(value = "是否运输爆炸品 0：不是  1：是")
    private Integer isExplode;

    /**
     * 危险货物承运人责任保险投保金额 
     */
    @ApiModelProperty(value = "危险货物承运人责任保险投保金额")
    private Double insureMoney;

    /**
     * 危险货物承运人责任保险有效期至 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "危险货物承运人责任保险有效期至")
    private Date insureEndDate;

    /**
     * 默认驾驶员id 
     */
    @ApiModelProperty(value = "默认驾驶员id")
    private String defaultPilot;

    /**
     * 默认押运员id 
     */
    @ApiModelProperty(value = "默认押运员id")
    private String defaultSupercargo;

    /**
     * 默认挂车id 
     */
    @ApiModelProperty(value = "默认挂车id")
    private String defaultCar;

    /**
     * 默认挂车车牌号 
     */
    @ApiModelProperty(value = "默认挂车车牌号")
    private String defaultCarCode;

    /**
     * 车辆技术等级 
     */
    @ApiModelProperty(value = "车辆技术等级")
    private String vehicleRank;

    /**
     * 等级评定日期 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "等级评定日期")
    private Date nextTecDate;

    /**
     * 二级维护日期 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "二级维护日期")
    private Date nextMaintainDate;

    /**
     * 年审有效截止期 
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "年审有效截止期")
    private Date nextCheckDate;

    /**
     * 资质照片 
     */
    @ApiModelProperty(value = "资质照片")
    private String certPhoto;

    /**
     * 车辆照片 
     */
    @ApiModelProperty(value = "车辆照片")
    private String carPhoto;

    /**
     * 罐体检测报告编号 
     */
    @ApiModelProperty(value = "罐体检测报告编号")
    private String testNo;

    /**
     * 运输范围
     */
    @ApiModelProperty(value = "运输范围")
    private String transportScope;

    /**
     * 地图车辆颜色：2绿色：无运单，3黄色：有运单空车（运单状态已出发-装货完成完成，卸货完成-运单完成），4蓝色：有运单重车（装货完成-卸货完成），另外，1，5状态不存库  ，1红色：车辆异常（t_gps_vehiclegps表中alarmState>0的时候表示异常）5灰色：离线（t_gps_vehiclegps表中is_online=0的时候表示离线）
     */
    @ApiModelProperty(value = "地图车辆颜色：2绿色：无运单，3黄色：有运单空车（运单状态已出发-装货完成完成，卸货完成-运单完成），4蓝色：有运单重车（装货完成-卸货完成），另外，1，5状态不存库  ，1红色：车辆异常（t_gps_vehiclegps表中alarmState>0的时候表示异常）5灰色：离线（t_gps_vehiclegps表中is_online=0的时候表示离线）")
    private Integer mapColor;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;


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
}