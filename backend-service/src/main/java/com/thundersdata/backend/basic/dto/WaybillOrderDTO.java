package com.thundersdata.backend.basic.dto;

import com.thundersdata.backend.basic.model.WaybillOrderWithBLOBs;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 订单DTO
 * auth yxw
 */
@ApiModel("电子运单实体类")
@Data
public class WaybillOrderDTO extends WaybillOrderWithBLOBs {


    /**
     * 运单编号
     */
    @ApiModelProperty(value = "运单编号")
    private String ordercode;


    /**
     * 托运单编号
     */
    @ApiModelProperty(value = "托运单编号")
    private String consignCode;

    /**
     * 驾驶员姓名
     */
    @ApiModelProperty(value = "驾驶员姓名")
    private String pilotName;

    /**
     * 驾驶员电话
     */
    @ApiModelProperty(value = "驾驶员电话")
    private String pilotPhone;

    /**
     * 驾驶员证件号码
     */
    @ApiModelProperty(value = "驾驶员证件号码")
    private String pilotCard;

    /**
     * 押运员姓名
     */
    @ApiModelProperty(value = "押运员姓名")
    private String supercargoName;

    /**
     * 押运员电话
     */
    @ApiModelProperty(value = "押运员电话")
    private String supercargoPhone;

    /**
     * 押运员证件号码
     */
    @ApiModelProperty(value = "押运员证件号码")
    private String supercargoCard;

    /**
     * 车辆车牌号
     */
    @ApiModelProperty(value = "车辆车牌号")
    private String vehicleCode;

    /**
     * 车辆运输许可证
     */
    @ApiModelProperty(value = "车辆运输许可证")
    private String vehicleCard;

    /**
     * 挂车车牌号
     */
    @ApiModelProperty(value = "挂车车牌号")
    private String trailerCode;

    /**
     * 挂车运输许可证
     */
    @ApiModelProperty(value = "驾驶员姓名")
    private String trailerCard;

    /**
     * 托运企业名称
     */
    @ApiModelProperty(value = "托运企业名称")
    private String shipperName;

    /**
     * 托运企业联系人
     */
    @ApiModelProperty(value = "托运企业联系人")
    private String shipperContact;

    /**
     * 托运企业联系电话
     */
    @ApiModelProperty(value = "托运企业联系电话")
    private String shipperContactPhone;

    /**
     * 托运企业经营许可证
     */
    @ApiModelProperty(value = "托运企业经营许可证")
    private String shipperCreditCode;

    /**
     * 收货企业
     */
    @ApiModelProperty(value = "收货企业")
    private String receivingName;


    /**
     * 收货企业联系人
     */
    @ApiModelProperty(value = "收货企业联系人")
    private String receivingContact;

    /**
     * 收货企业联系电话
     */
    @ApiModelProperty(value = "收货企业联系电话")
    private String receivingContactPhone;

    /**
     * 收货企业经营许可证
     */
    @ApiModelProperty(value = "收货企业经营许可证")
    private String receivingCreditCode;

    /**
     * 装货企业
     */
    @ApiModelProperty(value = "装货企业")
    private String expediterName;

    /**
     * 装货企业联系人
     */
    @ApiModelProperty(value = "装货企业联系人")
    private String expediterContact;

    /**
     * 装货企业联系电话
     */
    @ApiModelProperty(value = "装货企业联系电话")
    private String expediterContactPhone;

    /**
     * 装货企业经营许可证
     */
    @ApiModelProperty(value = "装货企业经营许可证")
    private String expediterCreditCode;


    /**
     * 运输企业
     */
    @ApiModelProperty(value = "运输企业")
    private String shipmentName;

    /**
     * 运输企业联系人
     */
    @ApiModelProperty(value = "运输企业联系人")
    private String shipmentContact;

    /**
     * 运输企业联系电话
     */
    @ApiModelProperty(value = "运输企业联系电话")
    private String shipmentContactPhone;

    /**
     * 运输企业经营许可证
     */
    @ApiModelProperty(value = "运输企业经营许可证")
    private String shipmentCreditCode;

    /**
     * 当前节点名字
     */
    @ApiModelProperty(value = "当前节点名字")
    private String nodeName;

    /**
     * 运输获取列表
     */
    @ApiModelProperty(value = "运输获取列表")
    private List<WaybillOrderGoodsDTO> goodsList;


    /**
     * 运输节点流程
     */
    @ApiModelProperty(value = "运输节点流程")
    private List<WaybillOrderDetailNodeDTO> nodeList;

    /**
     * 调度人姓名
     */
    @ApiModelProperty(value = "调度人姓名")
    private String schedulingUserName;



}
