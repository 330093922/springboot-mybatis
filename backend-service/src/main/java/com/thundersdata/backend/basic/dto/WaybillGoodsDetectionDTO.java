package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 危险化学品货物-检查项
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDetectionDTO", description = "危险化学品货物-检查项")
public class WaybillGoodsDetectionDTO {


    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "货物id ")
    private Integer goodsId;

    @ApiModelProperty(value = "检查项id ")
    private Integer detectionId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;


}
