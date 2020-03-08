package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 危险化学品货物-安全卡
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsSecurityVO", description = "危险化学品货物-安全卡")
public class WaybillGoodsSecurityVO {

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

}
