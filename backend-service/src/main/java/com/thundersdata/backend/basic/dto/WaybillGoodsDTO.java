package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 危险化学品货物信息
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDTO", description = "危险化学品货物信息DTO类")
public class WaybillGoodsDTO {

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;

}
