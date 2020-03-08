package com.thundersdata.backend.common.utils;


import com.alibaba.fastjson.JSONObject;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数组工具类
 */
public class ArrayUtils {

    private ArrayUtils() {
    }


    public static Integer[] strToArray(String src, String spliter){
        String[] split = src.split(spliter);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            String s1 = split[i];
            if(StringUtils.isNotBlank(s1)){
                Integer j = Integer.parseInt(s1);
                list.add(j);
            }
        }
        Integer[] result = new Integer[list.size()];
        return list.toArray(result);
    }


    public static Integer[] jsonArrayToList(String str){
        List<Integer> dataListArray = JSONObject.parseArray(str, Integer.class);
        Integer[] ownerIds = new Integer[dataListArray.size()];
        dataListArray.toArray(ownerIds);

        return ownerIds;
    }
}
