package com.thundersdata.backend.basic.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname EnterpriseLlist
 * @Description 企业列表
 * @Date 2019/12/8 10:56
 * @Created wrc
 */
@ApiModel("企业列表")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EnterpriseLlistVo {

    @ApiModelProperty(value = "企业名称")
    private String ownerName;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "统一信用代码")
    private String creditCode;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    //企业id
    private  Integer[] dataList;
    //是否开启
    private Boolean isScope;
    /**
     * 企业类型集合
     */
    @ApiModelProperty(value = "企业类型集合")
    private List<Integer> ownerTypes;
}
