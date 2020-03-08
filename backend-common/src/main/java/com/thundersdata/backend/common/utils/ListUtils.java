package com.thundersdata.backend.common.utils;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * list工具类
 */
public class ListUtils {

    /**
     * 按照key生成去重断言
     *
     * @param keyExtractor 唯一key
     * @return 断言
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        // 判断条件，set进行去重
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * list去重方法
     *
     * @param keyExtractor 唯一key
     * @return 去重后的list
     */
    public static <T> List<T> listDistinctByKey(List<T> list, Function<? super T, ?> keyExtractor) {

        return list.stream().filter(distinctByKey(keyExtractor)).collect(Collectors.toList());
    }

    /**
     * list去除null
     *
     * @param list 集合
     */
    public static List removerNull(List list) {
        ArrayList<T> listNull = new ArrayList<>();
        listNull.add(null);
        list.removeAll(listNull);

        return list;
    }
}
