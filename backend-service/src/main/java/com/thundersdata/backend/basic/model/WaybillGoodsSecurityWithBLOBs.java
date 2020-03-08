package com.thundersdata.backend.basic.model;

import lombok.Data;

@Data
public class WaybillGoodsSecurityWithBLOBs extends WaybillGoodsSecurity {
    /**
     * 危险性
     */
    private String risk;

    /**
     * 储运要求
     */
    private String requirements;

    /**
     * 泄露处置
     */
    private String reveal;

    /**
     * 急救方法
     */
    private String emergency;

    /**
     * 灭火方法
     */
    private String extinguisher;

    /**
     * 防护措施
     */
    private String protective;
}