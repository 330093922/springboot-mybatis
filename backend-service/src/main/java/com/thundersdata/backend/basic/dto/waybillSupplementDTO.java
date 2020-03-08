package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thundersdata.backend.basic.vo.WaybillGoodsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Classname waybillSupplementDTO
 * @Description TODO  补录运单对象
 * @Date 2020/1/20 17:09
 * @Created wrc
 */
@ApiModel("补录电子运单")
@Data
public class waybillSupplementDTO {

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
     * 二维码信息字符串
     */
    @ApiModelProperty(value = "二维码信息字符串")
    private String qrCode;

    /**
     * 发货企业（托运企业）id
     */
    @ApiModelProperty(value = "托运企业id")
    private Integer shipperId;

    /**
     * 收货企业id
     */
    @ApiModelProperty(value = "收货企业id")
    private Integer receivingId;

    /**
     * 装货企业id
     */
    @ApiModelProperty(value = "装货企业id")
    private Integer expediterId;

    /**
     * 挂车id
     */
    @ApiModelProperty(value = "挂车id")
    private Integer trailerId;

    /**
     * 车辆id
     */
    @ApiModelProperty(value = "车辆id")
    private Integer vehicleId;

    /**
     * 装货地点
     */
    @ApiModelProperty(value = "装货地点")
    private String shipmentStart;

    /**
     * 卸货地点
     */
    @ApiModelProperty(value = "卸货地点")
    private String shipmentStop;

    /**
     * 是否城市配送
     */
    @ApiModelProperty(value = "城市配送")
    private Boolean isCity;

    /**
     * 预计装货时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "预计装货时间")
    private Date shipmentTime;

    /**
     * 预计到达日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "预计到达日期")
    private Date shipmentArrive;

    /**
     * 运输企业（承运）id (经营许可证：信用代码)
     */
    @ApiModelProperty(value = "运输企业id")
    private Integer shipmentId;

    /**
     * 驾驶员用户id
     */
    @ApiModelProperty(value = "驾驶员用户id")
    private Integer pilotId;

    /**
     * 押运员用户id
     */
    @ApiModelProperty(value = "押运员用户id")
    private Integer supercargoId;

    /**
     * 运单状态  0:新建 1:已调度 2:运输中 3:待审核 4:已完成 5:已拒绝
     */
    @ApiModelProperty(value = "运单状态")
    private Integer status;

    /**
     * 当前节点id
     */
    @ApiModelProperty(value = "当前节点id")
    private Integer nodeId;

    /**
     * 调度人
     */
    @ApiModelProperty(value = "调度人")
    private Integer schedulingUser;


    /**
     * 调度日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "调度日期")
    private Date schedulingDate;

    /**
     * 装货人
     */
    @ApiModelProperty(value = "装货人")
    private String expediter;

    /**
     * 装货人联系方式
     */
    @ApiModelProperty(value = "装货人联系方式")
    private String expediterPhone;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


    /**
     * 注意事项
     */
    @ApiModelProperty(value = "注意事项")
    private String attention;

    /**
     * 应急处置措施
     */
    @ApiModelProperty(value = "应急处置措施")
    private String measures;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    //货物信息
    @ApiModelProperty(value = "运输获取列表")
    List<WaybillOrderGoodsDTO> goodsList;






}
