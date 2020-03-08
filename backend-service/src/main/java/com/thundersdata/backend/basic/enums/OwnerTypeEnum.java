package com.thundersdata.backend.basic.enums;

public enum OwnerTypeEnum {
    /**
     * 生产
     */
    PRODUCE_OWNER(1, "生产"),

    /**
     * 经营
     */
    MANAGE_OWNER(2, "经营"),

    /**
     * 存储
     */
    STORE_OWNER(3, "存储"),

    /**
     * 使用
     */
    USE_OWNER(4, "使用"),

    /**
     * 运输
     */
    TRANSPORT_OWNER(5, "运输"),

    /**
     * 回收
     */
    RETRIEVE_OWNER(6, "回收");

    /**
     * 枚举值
     */
    private Integer value;
    /**
     * 枚举描述
     */
    private String name;

    OwnerTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String find(Integer value) {
        for (OwnerTypeEnum ownerTypeEnum : OwnerTypeEnum.values()) {
            if (ownerTypeEnum.value.equals(value)) {
                return ownerTypeEnum.name;
            }
        }
        return null;
    }
}
