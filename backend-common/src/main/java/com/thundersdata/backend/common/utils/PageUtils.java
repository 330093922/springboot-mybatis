package com.thundersdata.backend.common.utils;

import lombok.Data;
import org.springframework.util.Assert;

/**
 * 分页参数初始化
 */
@Data
public class PageUtils {

    /**
     * 默认页数
     */
    private static Integer DEFAULT_PAGE = 1;

    /**
     * 默认大小
     */
    private static Integer DEFAULT_SIZE = 10;

    /**
     * 默认页数
     */
    private Integer page = DEFAULT_PAGE;

    /**
     * 默认大小
     */
    private Integer pageSize = DEFAULT_SIZE;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 总记录数
     */
    private Integer total;

    /**
     * 从查询的结果集中，取记录的起始位置
     */
    private Integer offset;

    /**
     * 取记录数
     */
    private Integer limit;

    /**
     * 页码初始化
     *
     * @param page 页码
     * @return 页码
     */
    public static int page(Integer page) {
        if (null == page || page < 1) {
            page = DEFAULT_PAGE;
        }

        return page;
    }

    /**
     * 每页记录条数初始化
     *
     * @param pageSize 每页记录条数
     * @return 每页记录条数
     */
    public static int pageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_SIZE;
        }

        return pageSize;
    }

    /**
     * 偏移量初始化
     *
     * @param page     页码
     * @param pageSize 每页记录条数
     * @return 偏移量
     */
    public static int offset(int page, int pageSize) {
        return (page - 1) * pageSize;
    }

    /**
     * 分页参数初始化
     *
     * @param page     页码
     * @param pageSize 每页记录条数
     * @return 分页参数
     */
    public static PageParam init(Integer page, Integer pageSize) {
        page = page(page);
        pageSize = pageSize(pageSize);
        Integer limit = pageSize;
        Integer offset = offset(page, pageSize);

        return new PageParam(page, pageSize, limit, offset);
    }

    public PageUtils(Integer page, Integer pageSize, Integer totalRecords) {
        if (page != null) {
            this.page = page;
        }

        if (pageSize != null) {
            this.pageSize = pageSize;
        }

        Assert.notNull(totalRecords, "totalRecords不能为空");
        Assert.isTrue(this.pageSize != 0, "pageSize不能为0");

        this.total = totalRecords;
        this.totalPages = (totalRecords + this.pageSize - 1) / this.pageSize;

        Assert.isTrue(this.page >= 1 && this.page <= this.totalPages, "页码超出范围");

        offset = (this.page - 1) * this.pageSize;
        limit = this.pageSize;
    }
}
