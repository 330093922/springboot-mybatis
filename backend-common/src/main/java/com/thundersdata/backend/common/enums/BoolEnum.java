package com.thundersdata.backend.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于 是否 枚举
 */
public enum BoolEnum {
    /**
     * 肯定
     */
    YES(1, "是"),
    /**
     * 否定
     */
    NO(0, "否");

    private Integer value;
    private String desc;

    BoolEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static String findDesc(Integer value) {
        for (BoolEnum boolEnum : BoolEnum.values()) {
            if (boolEnum.value.equals(value)) {
                return boolEnum.desc;
            }
        }

        return null;
    }

    public static Integer valueOfDesc(String desc) {
        for (BoolEnum boolEnum : BoolEnum.values()) {
            if (boolEnum.desc.equals(desc)) {
                return boolEnum.value;
            }
        }

        return null;
    }


    public static Integer zeroIfNullOfDesc(String desc) {
        if (valueOfDesc(desc) == null) {
            return BoolEnum.NO.getValue();
        }

        return valueOfDesc(desc);
    }

    public static List<String> getAllDesc() {
        List<String> desc = new ArrayList<>();
        for (BoolEnum boolEnum : BoolEnum.values()) {
            desc.add(boolEnum.desc);
        }

        return desc;
    }
}
