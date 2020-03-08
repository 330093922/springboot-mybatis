package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 危险化学品货物信息,值对象
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsVO", description = "危险化学品货物信息")
public class WaybillGoodsVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "联合国货物编号 ")
    private String unNo;

    @ApiModelProperty(value = "货物名称")
    private String name;

    @ApiModelProperty(value = "类/项别")
    private String type;

    @ApiModelProperty(value = "包装类别")
    private String packagingType;

    @ApiModelProperty(value = "别名")
    private String alias;

    @ApiModelProperty(value = "密度（吨/立方）")
    private BigDecimal number;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "0:激活 1:禁止")
    private Byte status;

}
