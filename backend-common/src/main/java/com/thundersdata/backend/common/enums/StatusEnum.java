package com.thundersdata.backend.common.enums;

import lombok.Getter;

/**
 * 冻结状态
 */
@Getter
public enum StatusEnum {
    /**
     * 冻结
     */
    YES(1, "冻结"),
    /**
     * 未冻结
     */
    NO(0, "正常");

    private Integer status;
    private String desc;

    StatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static StatusEnum typeOfStatus(Integer status) {
        if (status != null) {
            for (StatusEnum statusEnum : StatusEnum.values()) {
                if (statusEnum.status.equals(status)) {
                    return statusEnum;
                }
            }
        }

        return null;
    }

    public static String descOfStatus(Integer status) {
        StatusEnum statusEnum = StatusEnum.typeOfStatus(status);
        if (statusEnum != null) {
            return statusEnum.getDesc();
        }

        return null;
    }

    public static boolean isRightStatus(Integer status) {
        return typeOfStatus(status) != null;
    }
}
