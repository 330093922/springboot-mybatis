package com.thundersdata.backend.basic.model;

import lombok.Data;

@Data
public class WaybillRouteWithBLOBs extends WaybillRoute {
    /**
     * 注意事项
     */
    private String notice;

    /**
     * 经纬度信息
     */
    private String map;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 权限参数1
     */
    private String dataList;

    /**
     * 权限参数2
     */
    private Boolean isScope;


}