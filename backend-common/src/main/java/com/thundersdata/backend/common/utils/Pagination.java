package com.thundersdata.backend.common.utils;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class Pagination<T> {
    private Integer page;

    private Integer pageSize;

    private Integer total;

    private List<T> list;

    public static <T> Pagination<T> newInstance(Integer page, Integer pageSize, Integer total, List<T> list) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setPage(page);
        pagination.setPageSize(pageSize);
        pagination.setTotal(total);
        pagination.setList(list);
        return pagination;
    }

    public static <T> Pagination<T> newInstance(PageParam pageParam, Integer total, List<T> list) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setPage(pageParam.getPage());
        pagination.setPageSize(pageParam.getPageSize());
        pagination.setTotal(total);
        pagination.setList(list);
        return pagination;
    }

    public static <T> Pagination<T> nullPage(Integer page, Integer pageSize) {
        return newInstance(page, pageSize, 0, Collections.emptyList());
    }

    public static <T> Pagination<T> nullPage(PageParam pageParam) {
        return newInstance(pageParam.getPage(), pageParam.getPageSize(), 0, Collections.emptyList());
    }

    public void setDataList(List<T> list) {
        this.list = list;
    }
}
