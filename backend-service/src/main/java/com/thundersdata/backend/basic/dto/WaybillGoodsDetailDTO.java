package com.thundersdata.backend.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 危险化学品货物附加信息
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDetailDTO", description = "危险化学品货物附加信息DTO")
public class WaybillGoodsDetailDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "危险化学品货物-检查项")
    List<WaybillGoodsDetectionDTO> listDetection;

    @ApiModelProperty(value = "危险化学品货物-操作规程")
    List<WaybillGoodsDisciplineDTO> listDiscipline;

    @ApiModelProperty(value = "危险化学品货物-安全卡")
    WaybillGoodsSecurityDTO security;


}
