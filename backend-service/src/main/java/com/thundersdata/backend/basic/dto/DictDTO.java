package com.thundersdata.backend.basic.dto;

import lombok.Data;

/**
 * 通用下拉框实体类
 */
@Data
public class DictDTO {

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 扩展字段
     */
    private String filed;
}
