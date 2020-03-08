package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname WaybillOwnerDTO
 * @Description 企业下拉框DTO
 * @Date 2019/12/7 13:53
 * @Created wrc
 */
@ApiModel("企业下拉框")
@Data
@Component
public class WaybillOwnerDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    private String contact;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String ownerName;
    /**
     * 企业类型(运输、危化、经营) 1.生产、2.经营、3.存储、4.使用、5.运输、6.回收
     */
    @ApiModelProperty(value = "企业类型")
    private String ownerType;
    //经营许可证
    @ApiModelProperty(value = "经营许可证")
    private String creditCode;

    /**
     * 管辖区域
     */
    @ApiModelProperty(value = "管辖区域")
    private String governArea;

    /**
     * 经营许可证到期时间
     */
    @ApiModelProperty(value = "经营许可证到期时间")
    private Date creditEndDate;

    /**
     * 省份
     */
    @ApiModelProperty(value = "省份")
    private Integer province;

    /**
     * 城市
     */
    @ApiModelProperty(value = "城市")
    private Integer city;

    /**
     * 区县
     */
    @ApiModelProperty(value = "区县")
    private Integer county;

}
