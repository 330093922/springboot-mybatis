package com.thundersdata.backend.common.enums;

import lombok.Getter;

/**
 * 客户配送周期枚举
 * @author nonfou
 */
@Getter
public enum DeliveryCycleEnum {
    /**
     * 周一
     */
    MON(1, "周一"),
    /**
     * 周二
     */
    TUES(2, "周二"),
    /**
     * 周三
     */
    WED(3, "周三"),
    /**
     * 周四
     */
    THUR(4, "周四"),
    /**
     * 周五
     */
    FRI(5, "周五"),
    /**
     * 周六
     */
    SAT(6, "周六"),
    /**
     * 周日
     */
    SUN(7, "周日");

    /**
     * 代码
     */
    private Integer value;
    /**
     * 名称
     */
    private String name;

    DeliveryCycleEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Integer valueOfName(String name) {
        for (DeliveryCycleEnum deliveryCycleEnum : DeliveryCycleEnum.values()) {
            if (deliveryCycleEnum.name.equals(name)) {
                return deliveryCycleEnum.value;
            }
        }

        return null;
    }
}
