package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 危险化学品货物-操作规程
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDisciplineVO", description = "危险化学品货物-操作规程")
public class WaybillGoodsDisciplineVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "货物id ")
    private Integer goodsId;

    @ApiModelProperty(value = "操作名称")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;

}
