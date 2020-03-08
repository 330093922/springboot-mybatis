package com.thundersdata.backend.basic.vo;

import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SelectConsignList
 * @Description: TODO
 * @Author haibo
 * @Date 2020/2/1
 * @Version V1.0
 **/
@Data
public class SelectConsignList extends PageVo {

    @ApiModelProperty(value = "托运单号")
    private String consignCode;

    @ApiModelProperty(value = "托运企业")
    private String owner;

    @ApiModelProperty(value = "状态列表", required = true)
    List<Integer> states;
}
