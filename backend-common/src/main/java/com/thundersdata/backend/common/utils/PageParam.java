package com.thundersdata.backend.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页相关参数
 */
@Data
@AllArgsConstructor
public class PageParam {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 查询数量
     */
    private Integer limit;
    /**
     * 偏移量；从查询的结果集中，取记录的起始位置
     */
    private Integer offset;
}
