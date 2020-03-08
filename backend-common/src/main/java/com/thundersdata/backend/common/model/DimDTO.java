package com.thundersdata.backend.common.model;

import lombok.Data;

/**
 * system模块 字典DTO
 */
@Data
public class DimDTO {

    /**
     * id
     */
    Integer id;

    /**
     * 中文name
     */
    String name;
}
