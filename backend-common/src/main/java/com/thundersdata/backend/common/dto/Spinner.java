package com.thundersdata.backend.common.dto;

import lombok.Data;

/**
 * 下拉列表展示
 */
@Data
public class Spinner {
    /**
     * key
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 下拉值
     */
    private Integer value;
}
