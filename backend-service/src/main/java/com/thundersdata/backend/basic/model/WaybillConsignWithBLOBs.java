package com.thundersdata.backend.basic.model;

import lombok.Data;

@Data
public class WaybillConsignWithBLOBs extends WaybillConsign {
    /**
     * 注意事项
     */
    private String attention;

    /**
     * 应急处置措施（急救措施）
     */
    private String measures;

    /**
     * 备注
     */
    private String remark;

}