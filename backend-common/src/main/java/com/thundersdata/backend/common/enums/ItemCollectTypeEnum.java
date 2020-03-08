package com.thundersdata.backend.common.enums;

import lombok.Getter;

/**
 * 商品采集类型枚举
 */
@Getter
public enum ItemCollectTypeEnum {

    /**
     * 不采集
     */
    NOT_COLLECTION(0, "不采集"),

    /**
     * 生产日期
     */
    PRODUCE_DATE(1, "生产日期"),
    /**
     * 到期日期
     */
    EXPIRY_DATE(2, "到期日期");

    /**
     * 枚举值
     */
    private Integer value;
    /**
     * 枚举描述
     */
    private String name;

    ItemCollectTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static ItemCollectTypeEnum find(Integer value) {
        for (ItemCollectTypeEnum itemCollectTypeEnum : ItemCollectTypeEnum.values()) {
            if (itemCollectTypeEnum.value.equals(value)) {
                return itemCollectTypeEnum;
            }
        }

        return null;
    }
}
