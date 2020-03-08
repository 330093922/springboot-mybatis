package com.thundersdata.backend.common.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtils {

    public static final String ISO_LOCAL_DATE_SLASH = "yyyy/MM/dd";

    private static final String ISO_LOCAL_DATE_ISO_TIME = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    public static String formatToStr(Date date) {
        return formatToStr(date, ZoneId.systemDefault(), DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private static String formatToStr(Date date, DateTimeFormatter dateTimeFormatter) {
        return formatToStr(date, ZoneId.systemDefault(), dateTimeFormatter);
    }

    private static String formatToStr(Date date, ZoneId zoneId, DateTimeFormatter dateTimeFormatter) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zoneId);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 针对 @{@link Date} 转为 {yyyyMMdd} 格式字符串
     *
     * @param date 日期格式
     * @return yyyyMMdd 格式字符串
     */
    public static String toBasicIsoStr(Date date) {
        return formatToStr(date, DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 针对 {@link Date} 转为 {yyyy-MM-dd} 格式字符串
     *
     * @param date 日期格式
     * @return yyyy-MM-dd
     */
    public static String toLocalDateStr(Date date) {
        return formatToStr(date, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 针对 {@link Date} 转为 {yyyy-MM-dd HH:mm:ss} 格式字符串
     *
     * @param date 日期
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String toLocalDateIsoTimeStr(Date date) {
        // exclude the NPE.
        if (date == null) {
            return null;
        }

        return formatToStr(date, DateTimeFormatter.ofPattern(ISO_LOCAL_DATE_ISO_TIME));
    }

    /**
     * 针对日期格式(yyyy-MM-dd) 转为 {@link Date}
     *
     * @param time yyyy-MM-dd
     * @return {@link Date} 类型
     */
    public static Date localDateStrToDate(String time) {
        if (time == null) {
            return null;
        }

        return localDateStrToDate(time, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    /**
     * 针对日期格式 yyyyMMdd  转为 {@link Date}
     *
     * @param time yyyyMMdd
     * @return 日期类型
     */
    public static Date basicIsoStrToDate(String time) {
        if (time == null) {
            return null;
        }

        return localDateStrToDate(time, DateTimeFormatter.BASIC_ISO_DATE);
    }

    /**
     * 针对日期字符串 yyyy-MM-dd HH:mm:ss 转为{@link Date}
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 日期
     */
    public static Date localDateIsoTimeToDate(String time) {
        return localDateTimeStrToDate(time, DateTimeFormatter.ofPattern(ISO_LOCAL_DATE_ISO_TIME));
    }

    /**
     * 年月日 格式 ==> {@link Date}
     *
     * @param time              年月日 (默认当前时间的时分秒)
     * @param dateTimeFormatter formatter格式
     * @return {@link Date}
     */
    private static Date localDateStrToDate(String time, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.parse(time, dateTimeFormatter), LocalTime.now());
        return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }

    /**
     * (年月日 + 时分秒) 格式==> {@link Date}
     *
     * @param time              年月日 时分秒
     * @param dateTimeFormatter formatter格式
     * @return {@link Date}
     */
    private static Date localDateTimeStrToDate(String time, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(time, dateTimeFormatter);
        return Date.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()).toInstant());
    }
}
