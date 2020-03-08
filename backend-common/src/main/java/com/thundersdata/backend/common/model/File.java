package com.thundersdata.backend.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description： files表的实体类
 */
@Data
public class File {
    /**
     * 文件主键(与文件服务共用FILEID）
     */
    private Integer id;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 附件相对路径地址
     */
    private String fileUrl;

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
     * 删除标记
     */
    private Byte deleted;

    public File() {

    }

    public File(Integer id, String fileName, String fileUrl) {
        this.id = id;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }
}
