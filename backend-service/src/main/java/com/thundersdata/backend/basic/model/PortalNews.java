package com.thundersdata.backend.basic.model;

import lombok.Data;

import java.util.Date;

/**
 * 描述:portal_news表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-01-22
 */
@Data
public class PortalNews {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题 
     */
    private String title;

    /**
     * 0:no 1:yes
     */
    private Integer isTop;

    /**
     * 摘要内容 默认取主文前100个字符
     */
    private String digest;

    /**
     * 类型 0：工作动态， 1：法律法规， 2：通知公告， 3：危化百科
     */
    private Integer type;

    /**
     * 发布人 
     */
    private String userId;

    /**
     * 来源 
     */
    private String source;

    /**
     * 备用字段
     */
    private String filed;

    /**
     * 0:激活 1:禁止
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 是否删除
     */
    private Byte isDeleted;

    /**
     * 主内容
     */
    private String content;
}