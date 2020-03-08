package com.thundersdata.backend.common.enums;

/**
 * @Description: 空闲/占用枚举
 */
public enum FreeEnum {
    /**
     * 空闲
     */
    FREE(0, "空闲"),
    /**
     * 占用
     */
    OCCUPY(1, "占用");

    private int code;
    private String desc;

    FreeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getCode() {
        return this.code;
    }
}
