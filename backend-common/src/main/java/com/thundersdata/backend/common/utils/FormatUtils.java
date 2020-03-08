package com.thundersdata.backend.common.utils;

import org.springframework.util.StringUtils;

public class FormatUtils {
    /**
     * 构造模糊匹配的字符串
     *
     * @param value
     * @return
     */
    public static String makeFuzzySearchTerm(String value) {
        value = FormatUtils.trimToNull(value);
        if (value == null) {
            return null;
        }

        return '%' + value + '%';
    }

    /**
     * 将一个字符串首尾空格去掉，如果为空，返回null
     *
     * @param val 待处理的字符串
     * @return
     */
    public static String trimToNull(String val) {
        val = StringUtils.trimWhitespace(val);
        if (val.isEmpty()) {
            val = null;
        }

        return val;
    }
}
