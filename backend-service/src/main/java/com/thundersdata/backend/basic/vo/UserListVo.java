package com.thundersdata.backend.basic.vo;


import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单查询")
@Data
public class UserListVo extends PageVo {

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "证件号码")
    private String idCard;

    @ApiModelProperty(value = "联系电话")
    private String phone;


}
