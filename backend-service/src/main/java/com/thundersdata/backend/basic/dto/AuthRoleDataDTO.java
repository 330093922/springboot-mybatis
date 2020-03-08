package com.thundersdata.backend.basic.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thundersdata.backend.basic.model.AuthRoleData;
import lombok.Data;

import java.util.Date;

@Data
public class AuthRoleDataDTO extends AuthRoleData {

    /**
     * 是否加载列表 如果为true 则加载数据单位列表 False则不控制范围
     */
    private Boolean isScope = true;


    // 企业数据

    /**
     * 企业id
     */
    private Integer ownerId;

    /**
     * 企业类型(运输、危化、经营) 1.生产、2.经营、3.存储、4.使用、5.运输、6.回收
     */
    private String ownerType;

    /**
     * 企业名称
     */
    private String ownerName;

    /**
     * 企业简称
     */
    private String ownerJc;

    /**
     * 注册资本
     */
    private String registerCapital;

    /**
     * 注册时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date registerTime;

    /**
     * 厂区面积
     */
    private Double area;

    /**
     * 在职人数
     */
    private String personNum;

    /**
     * 工商注册号
     */
    private String icbcCode;

    /**
     * 统一信用代码
     */
    private String creditCode;

    /**
     * 经营地址
     */
    private String ownerAddr;

    /**
     * 注册地址
     */
    private String registerAddr;

    /**
     * 经营地址所在区域
     */
    private String mgrCode;

    /**
     * 注册地址所在区域
     */
    private String registerCode;

    /**
     * 经营范围 字符串
     */
    private String mgrArea;

    /**
     * 经营状态 0：停业；1：营业；9：注销
     */
    private Integer state;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 所属管辖范围 12位行政区划code
     */
    private String governCode;

    /**
     * 重大危险源级别
     */
    private Integer importantLevel;

    /**
     * 是否涉及重点危险化学品 1是，0否
     */
    private Integer isImportantChemical;

    /**
     * 是否涉及重点化工工艺  1是，0否
     */
    private Integer isImportantCrafts;

    /**
     * 0：新注册 1：乡镇审核中  2：区县审核中  3：市级审核中  5：审核通过  9：驳回
     */
    private Integer ownerZt;

    /**
     * 审核反馈信息
     */
    private String ownerZtMsg;

    /**
     * 企业性质
     */
    private Integer ownerNature;

    /**
     * 法人姓名
     */
    private String corpName;

    /**
     * 法人证件类型
     */
    private Integer corpCard;

    /**
     * 法人证件号码
     */
    private String corpCardNumber;

    /**
     * 法人联系电话
     */
    private String corpPhone;

    /**
     * 法人照片
     */
    private String corpPhoto;

    /**
     * 所属行业
     */
    private Integer industry;

    /**
     * 经度
     */
    private String lon;

    /**
     * 维度
     */
    private String lat;

    /**
     * 等级
     */
    private String businessLevelCount;

    /**
     * 工艺
     */
    private String businessTechnology;

    /**
     * 营业范围
     */
    private String businessGoods;

    /**
     * 外地企业（0：否；1：是；）
     */
    private String nonnative;
}
