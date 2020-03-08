package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_camera表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@ApiModel("摄像头")
@Data
public class WaybillCameraDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "摄像头id")
    private Integer id;

    /**
     * 所属企业id
     */
    @ApiModelProperty(value = "所属企业id")
    private Integer ownerId;

    /**
     * 所属单位名称
     */
    @ApiModelProperty(value = "所属单位名称")
    private String ownerName;

    /**
     * 设备编号 
     */
    @ApiModelProperty(value = "设备编号")
    private String equipmentNo;

    /**
     * 设备状态 1：正常；2：异常; 3：停用 
     */
    @ApiModelProperty(value = "设备状态 1：正常；2：异常; 3：停用 ")
    private Integer equipmentStatus;

    /**
     * 视频读取方式分类 0：流地址 1：用户名密码
     */
    @ApiModelProperty(value = "视频读取方式分类 0：流地址 1：用户名密码")
    private String type;

    /**
     * 安装区域 1.厂区门口 2.磅区 3.装卸区车位 4.其他
     */
    @ApiModelProperty(value = "安装区域 1.厂区门口 2.磅区 3.装卸区车位 4.其他")
    private String installArea;

    /**
     * 用户id 
     */
    @ApiModelProperty(value = "用户id")
    private String userId;

    /**
     * 密码 
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 显示名称 
     */
    @ApiModelProperty(value = "显示名称")
    private String name;

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
     * rtmp流地址 
     */
    @ApiModelProperty(value = "rtmp流地址")
    private String rtmpUrl;

    /**
     * hls地址 
     */
    @ApiModelProperty(value = "（视频播放）hls地址")
    private String hlsUrl;

    /**
     * 0:激活 1:禁止
     */
    @ApiModelProperty(value = "状态 0:激活 1:禁止")
    private Byte status;

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

 }