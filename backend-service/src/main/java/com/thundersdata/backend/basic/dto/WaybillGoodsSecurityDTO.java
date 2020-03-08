package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 危险化学品货物-安全卡
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsSecurityDTO", description = "危险化学品货物-安全卡")
public class WaybillGoodsSecurityDTO {


    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "货物id ")
    private Integer goodsId;

    @ApiModelProperty(value = "安全图片1")
    private String imageOne;

    @ApiModelProperty(value = "安全图片2")
    private String imageTow;

    @ApiModelProperty(value = "危险性")
    private String risk;

    @ApiModelProperty(value = "储运要求")
    private String requirements;

    @ApiModelProperty(value = "泄露处置")
    private String reveal;

    @ApiModelProperty(value = "急救方法")
    private String emergency;

    @ApiModelProperty(value = "灭火方法")
    private String extinguisher;

    @ApiModelProperty(value = "防护措施")
    private String protective;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;


}
