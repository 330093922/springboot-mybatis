package com.thundersdata.backend.basic.model;

import lombok.Data;

@Data
public class WaybillDetectionWithBLOBs extends WaybillDetection {
    /**
     * 检查内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;
}