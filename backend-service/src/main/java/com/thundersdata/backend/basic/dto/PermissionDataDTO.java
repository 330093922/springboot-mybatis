package com.thundersdata.backend.basic.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissionDataDTO {

    /**
     * 数据范围
     */
    private List<Integer> dataList;

    /**
     * 是否进行数据范围控制
     */
    private Boolean isScope = true;
}
