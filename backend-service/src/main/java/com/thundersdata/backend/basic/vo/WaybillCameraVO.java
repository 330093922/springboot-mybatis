package com.thundersdata.backend.basic.vo;

import com.thundersdata.backend.common.model.PageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 描述:waybill_camera表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@ApiModel("摄像头")
@Data
public class WaybillCameraVO extends PageVo {


    /**
     * 设备编号
     */
    @ApiModelProperty(value = "设备编号")
    private String equipmentNo;



    /**
     * 安装区域 1.厂区门口 2.磅区 3.装卸区车位 4.其他
     */
    @ApiModelProperty(value = "安装区域 1.厂区门口 2.磅区 3.装卸区车位 4.其他")
    private String installArea;



    /**
     * 显示名称
     */
    @ApiModelProperty(value = "显示名称")
    private String name;



}