package com.thundersdata.backend.basic.dto;

import com.thundersdata.backend.basic.model.WaybillOrderNode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 节点详情
 * yxw
 */
@ApiModel("节点详情")
@Data
public class WaybillOrderDetailNodeDTO extends WaybillOrderNode {

    /**
     * 节点名字
     */
    @ApiModelProperty(value = "节点名字")
    private String nodeName;

}
