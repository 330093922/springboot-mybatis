package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述:waybill_user表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@Data
@Component
@ApiModel("企业人员")
public class WaybillUser {
    /**
     * 人员编号 identity (1,1) not null 
     */
    @ApiModelProperty(value = "人员id", required = true)
    private Integer id;

    /**
     * 所属单位id 
     */
    @ApiModelProperty(value = "所属单位id", required = true)
    private String ownerId;

    /**
     * 姓名 
     */
    @ApiModelProperty(value = "姓名", required = true)
    private String name;

    /**
     * 从业类型 0:装卸管理员 1:特种设备作业人员 2:驾驶员 3:押运员 4:其他人员
     */
    @ApiModelProperty(value = "从业类型 0:装卸管理员 1:特种设备作业人员 2:驾驶员 3:押运员 4:其他人员", required = true)
    private String type;

    /**
     * 性别 0:女  1:男
     */
    @ApiModelProperty(value = "性别 0:女  1:男 ", required = true)
    private Integer sex;

    /**
     * 年龄 
     */
    @ApiModelProperty(value = "年龄", required = true)
    private Integer age;

    /**
     * 出生年月 
     */
    @ApiModelProperty(value = "出生年月", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;

    /**
     * 证件类型 0：其他  1：身份证   2：护照
     */
    @ApiModelProperty(value = "证件类型 0：其他  1：身份证   2：护照", required = true)
    private Integer cardType;

    /**
     * 证件号码 
     */
    @ApiModelProperty(value = "证件号码", required = true)
    private String idCard;

    /**
     * 联系电话 
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 家庭住址地区编号 
     */
    @ApiModelProperty(value = "家庭住址地区编号", required = true)
    private String addrPlace;

    /**
     * 地址 
     */
    @ApiModelProperty(value = "地址", required = true)
    private String address;

    /**
     * 从业资格证编号
     */
    @ApiModelProperty(value = "从业资格证编号", required = true)
    private String employedThe;

    /**
     * 从业资格证到期时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "从业资格证到期时间", required = true)
    private Date employedEndDate ;


     /**
     * 籍贯
     */
    @ApiModelProperty(value = "籍贯", required = true)
    private String nativePlace;

    /**
     * 人员照片 
     */
    @ApiModelProperty(value = "人员照片", required = true)
    private String photoUrl;

    /**
     * 审核状态 0不需要审核 1乡镇审核中 2区县审核中 3市级审核中 4审核通过 9驳回
     */
    @ApiModelProperty(value = "审核状态 0不需要审核 1乡镇审核中 2区县审核中 3市级审核中 4审核通过 9驳回", required = true)
    private Integer reviewStatus;

    /**
     * 是否离职  0:离职   1:入职
     */
    @ApiModelProperty(value = "是否离职  0:离职   1:入职", required = true)
    private Integer flag;

    /**
     * 入职时间
     */
    @ApiModelProperty(value = "入职时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date joinTime;

    /**
     * 合同到期时间
     */
    @ApiModelProperty(value = "合同到期时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 登陆用户名
     */
    @ApiModelProperty(value = "登陆用户名", required = true)
    private String username;

    /**
     * 登陆密码
     */
    @ApiModelProperty(value = "登陆密码", required = true)
    private String password;

    /**
     * token
     */
    @ApiModelProperty(value = "token", required = true)
    private String token;

    /**
     * 0:司机 1:调度员
     */
    @ApiModelProperty(value = "0:司机 1:调度员", required = true)
    private Integer loginType;

    /**
     * 0:激活 1:禁止
     */
    @ApiModelProperty(value = "0:激活 1:禁止", required = true)
    private Byte status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", required = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 人脸特征值 二进制字符串
     */
    @ApiModelProperty(value = "人脸特征值 二进制字符串", required = true)
    private String feature;

    @ApiModelProperty(value = "0:可以拉货 1：不能拉货", required = true)
    //0:可以拉货 1：不能拉货
    private Integer state;

}