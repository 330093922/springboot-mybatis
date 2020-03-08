package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 危险化学品货物关联信息
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDetailVO", description = "危险化学品货物关联信息")
public class WaybillGoodsDetailVO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "危险化学品货物-检查项")
    List<WaybillGoodsDetectionVO> listDetection;

    @ApiModelProperty(value = "危险化学品货物-操作规程")
    List<WaybillGoodsDisciplineVO> listDiscipline;

    @ApiModelProperty(value = "危险化学品货物-安全卡")
    WaybillGoodsSecurityVO security;

}
