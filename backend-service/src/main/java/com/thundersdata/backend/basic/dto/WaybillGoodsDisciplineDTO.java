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
 * 危险化学品货物-操作规程
 * </p>
 *
 * @author haizi
 * @since 2019-12-13
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "WaybillGoodsDisciplineDTO", description = "危险化学品货物-操作规程")
public class WaybillGoodsDisciplineDTO {


    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "货物id ")
    private Integer goodsId;

    @ApiModelProperty(value = "操作名称")
    private String name;

    @ApiModelProperty(value = "内容")
    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;


}
