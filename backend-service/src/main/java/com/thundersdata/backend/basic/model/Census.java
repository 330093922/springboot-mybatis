package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.convert.ThreeTenBackPortConverters;

import java.util.Date;

@Data
public class Census {
    private Integer countNum;

    private String wayBillType;

    private String countType;

    private String pname;

    private Integer ownerType;

    private String ownerTypeCn;

    private Integer vehicle_id;

    private String vehicle_code;

    private Integer shipment_id;

    private String owner_name;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date created_at;

    private Double percent;

    //已完成运单统计数
    private Integer downNum;

    //未完成运单统计数
    private Integer goingNum;

    //仅包含月份和天数的时间
    private String createTime;
}
