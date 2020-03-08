package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 描述:waybill_area表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaybillArea {
    /**
     * id
     */
    private Integer id;

    //企业id
    private Integer ownerId;

    /**
     * 装卸区域编号 
     */
    private String areaNo;

    /**
     * 装卸区域名称
     */
    private String areaName;

    /**
     * 装卸区类型：0:装卸货 1:装货 2:卸货
     */
    private Byte type;

    /**
     * 可装卸货货物名称
     */
    private String goodsName;

    /**
     * 危险特性分类
     */
    private String classify;

    /**
     * 投入日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date inputDate;

    /**
     * 容积（立方米）
     */
    private Integer volume;

    /**
     * 长（米）
     */
    private Integer length;

    /**
     * 宽（米）
     */
    private Integer wide;

    /**
     * 装卸区域实景
     */
    private String imageUrl;

    /**
     * 状态：0:激活 1:禁止
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 备注
     */
    private String remark;

}