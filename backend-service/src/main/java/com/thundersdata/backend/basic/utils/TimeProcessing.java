package com.thundersdata.backend.basic.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Classname TimeProcessing
 * @Description TODO
 * @Date 2020/2/7 13:00
 * @Created wrc
 */
public class TimeProcessing {

    public static List<String> findDates(String dBegin, String dEnd) throws ParseException, ParseException {
        // 日期工具类准备
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        // 设置开始时间
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(dBegin));

        // 设置结束时间
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(format.parse(dEnd));

        // 装返回的日期集合容器
        List<String> Datelist = new ArrayList<String>();

        // 每次循环给calBegin日期加一天，直到calBegin.getTime()时间等于dEnd
        while (format.parse(dEnd).after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Datelist.add(format.format(calBegin.getTime()));
        }
        Datelist.add(dBegin);
        List<String> list = new ArrayList<String>();
        // 将日期格式2019-01-21 转换成 20190121
        for (int i = 0; i < Datelist.size(); i++) {
            String date = Datelist.get(i);
            //String day = date.substring(0, 4) + date.substring(5, 7) + date.substring(8) + "";
            list.add(date);
        }
        return list;
    }


}
