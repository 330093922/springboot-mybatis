package com.thundersdata.backend.common.utils;


import org.springframework.util.StringUtils;

/**
 * 字符串工具类
 */
public class StrUtils {

    private StrUtils() {
    }

    /**
     * 连接字符串
     *
     * @param resultStr 结果字符串
     * @param appendStr 要连接的字符串
     */
    public static void appendEmptyIfNull(StringBuilder resultStr, String appendStr) {
        if (!StringUtils.isEmpty(appendStr)) {
            resultStr.append(appendStr);
        }
    }

    /**
     * 判断字符串是否由纯数字组成
     *
     * @param str 字符串
     * @return true 纯数字组成的字符串
     */
    public static boolean isDigitStr(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
