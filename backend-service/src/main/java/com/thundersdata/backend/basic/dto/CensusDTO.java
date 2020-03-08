package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel("数据统计实体类")
@Data
public class CensusDTO {
    @ApiModelProperty(value = "统计数量")
    private Integer countNum;

    @ApiModelProperty(value = "运单类型")
    private String wayBillType;

    @ApiModelProperty(value = "统计类型")
    private String countType;

    @ApiModelProperty(value = "驾驶员或押运员名字")
    private String pname;

    @ApiModelProperty(value = "企业类型")
    private Integer ownerType;

    @ApiModelProperty(value = "企业类型中文")
    private String ownerTypeCn;

    @ApiModelProperty(value = "运输车辆ID")
    private Integer vehicle_id;

    @ApiModelProperty(value = "车牌号")
    private String vehicle_code;

    @ApiModelProperty(value = "运输企业")
    private Integer shipment_id;

    @ApiModelProperty(value = "企业名称")
    private String owner_name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date created_at;

    @ApiModelProperty(value = "统计百分比")
    private Double percent;

    @ApiModelProperty(value = "已完成运单统计数")
    private Integer downNum;

    @ApiModelProperty(value = "未完成运单统计数")
    private Integer goingNum;

    @ApiModelProperty(value = "仅包含月份和天数的时间")
    private String createTime;
}
