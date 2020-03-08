package com.thundersdata.backend.common.utils;

import lombok.Data;

/**
 * 实例域属性
 */
@Data
public class FieldProperty {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * has + fieldName
     */
    private String hasMethodName;
    /**
     * get + fieldName
     */
    private String getMethodName;
    /**
     * set + fieldName
     */
    private String setMethodName;
}
