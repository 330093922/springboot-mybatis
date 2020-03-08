package com.thundersdata.backend.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 描述:waybill_files表的实体类
 * @version
 * @author:  paris
 * @创建时间: 2019-12-06
 */
@Data
@Component
public class WaybillFiles {
    /**
     * 文件id
     */
    private Integer id;

    /**
     * 文件链接 
     */
    private String url;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件类型 0:图片  1:其他
     */
    private String type;

    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;
}