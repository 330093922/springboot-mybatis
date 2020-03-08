package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 描述:waybill_route表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@Data
@Component
@ApiModel("运输路线管理")
public class WaybillRoute {
    /**
     * id
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Integer id;

    /**
     * 所属企业
     */
    @ApiModelProperty(value = "所属企业ID", required = true)
    private Integer ownerId;

    /**
     * 线路名称
     */
    @ApiModelProperty(value = "线路名称", required = true)
    private String name;

    /**
     * 始发地
     */
    @ApiModelProperty(value = "始发地", required = true)
    private String startAddr;

    /**
     * 途径
     */
    @ApiModelProperty(value = "途径", required = true)
    private String way;

    /**
     * 里程
     */
    @ApiModelProperty(value = "里程", required = true)
    //private Integer km;
    private String km;
    /**
     * 终点站
     */
    @ApiModelProperty(value = "终点站", required = true)
    private String stopAddr;

    /**
     * 0:激活 1:禁止
     */
    @ApiModelProperty(value = "是否激活 0:激活 1:禁止", required = true)
    private Byte status;

    /**
     * 注意事项
     */
    @ApiModelProperty(value = "注意事项", required = true)
    private String notice;

    /**
     * 经纬度信息
     */
    @ApiModelProperty(value = "经纬度信息", required = true)
    private String map;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", required = true)
    private Date updatedAt;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除", required = true)
    private Byte isDeleted;

}