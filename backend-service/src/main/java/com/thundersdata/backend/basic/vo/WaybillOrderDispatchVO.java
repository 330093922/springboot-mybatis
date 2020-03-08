package com.thundersdata.backend.basic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thundersdata.backend.basic.dto.WaybillOrderDetailNodeDTO;
import com.thundersdata.backend.basic.dto.WaybillOrderGoodsDTO;
import com.thundersdata.backend.basic.model.WaybillOrderWithBLOBs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 订单DTO
 * auth yxw
 */
@ApiModel("电子运单派发VO")
@Data
public class WaybillOrderDispatchVO {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;

    /**
     * 托运单id
     */
    @ApiModelProperty(value = "托运单id")
    private Integer consignmentId;

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
     * 装货人姓名
     */
    @ApiModelProperty(value = "装货人姓名")
    private String expediter;


    /**
     * 装货人联系电话
     */
    @ApiModelProperty(value = "装货人联系电话")
    private String expediterPhone;

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
     * 车辆id
     */
    @ApiModelProperty(value = "车辆id")
    private Integer vehicleId;

    /**
     * 挂车id
     */
    @ApiModelProperty(value = "挂车id")
    private Integer trailerId;


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
     * 是否城市配送
     */
    @ApiModelProperty(value = "城市配送")
    private Boolean isCity;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

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
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Byte isDeleted;


}
