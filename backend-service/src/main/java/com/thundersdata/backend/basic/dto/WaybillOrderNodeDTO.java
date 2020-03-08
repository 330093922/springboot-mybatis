package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * 描述:waybill_order_node表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@ApiModel("节点详情")
@Data
public class WaybillOrderNodeDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 运单编号 
     */
    @ApiModelProperty(value = "运单编号")
    private String orderCode;

    /**
     * 节点id
     */
    @ApiModelProperty(value = "节点id")
    private Integer nodeId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 节点内容
     */
    @ApiModelProperty(value = "节点内容")
    private String content;

}