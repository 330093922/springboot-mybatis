package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 托运单货物DTO
 * auth wanghaibo
 */
@ApiModel("托运单货物DTO")
@Data
public class WaybillConsignGoodsDTO {

    /**
     * id
     */
    @ApiModelProperty(value = "ID")
    private Integer id;

    /**
     * 托运单id
     */
    @ApiModelProperty(value = "托运单id")
    private Integer consignId;

    /**
     * 货物id
     */
    @ApiModelProperty(value = "货物id")
    private Integer goodsId;

    /**
     * 货物联合国统一编号
     */
    @ApiModelProperty(value = "货物联合国统一编号")
    private String unNo;

    /**
     * 货物名称
     */
    @ApiModelProperty(value = "货物名称")
    private String goodsName;

    /**
     * 货物类/项别
     */
    @ApiModelProperty(value = "货物类/项别")
    private String goodsType;

    /**
     * 包装类别
     */
    @ApiModelProperty(value = "包装类别")
    private String packagingType;

    /**
     * 密度
     */
    @ApiModelProperty(value = "密度")
    private BigDecimal goodsDensity;

    /**
     * 剩余数量 剩余为0 不可操作
     */
    @ApiModelProperty(value = "剩余数量")
    private Integer surplusNo;

    /**
     * 总计数量
     */
    @ApiModelProperty(value = "总计数量")
    private Integer totalNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedAt;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
