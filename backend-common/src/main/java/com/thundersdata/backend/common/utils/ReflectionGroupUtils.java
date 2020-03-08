package com.thundersdata.backend.common.utils;

import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射分组工具类
 */
public class ReflectionGroupUtils {

    /**
     * 利用反射对list<bean>中bean的某属性进行分组
     *
     * @param mates 列表
     * @param fname 属性名
     * @param <T>   类型
     * @return 根据属性名分组的map
     */
    public static <T> Map<Object, List<T>> groupMapByField(List<T> mates, String fname) {
        Map<Object, List<T>> mateMap = new HashMap<Object, List<T>>();
        if (CollectionUtils.isEmpty(mates)) {
            return mateMap;
        }
        try {
            if (!CollectionUtils.isEmpty(mates)) {
                for (T mate : mates) {
                    Class<?> clazz = mate.getClass();
                    String methodname = "get" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
                    Method method = getMethod(clazz, methodname, new Class[]{});
                    Object key = method.invoke(mate);
                    if (mateMap.containsKey(key)) {
                        mateMap.get(key).add(mate);
                    } else {
                        List<T> list = new ArrayList<T>();
                        list.add(mate);
                        mateMap.put(key, list);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mateMap;
    }

    /**
     * 获取反射调用的方法-包含父类的
     */
    private static Method getMethod(Class clazz, String methodName,
                                    final Class[] classes) throws Exception {
        Method method = null;
        try {
            method = clazz.getDeclaredMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            try {
                method = clazz.getMethod(methodName, classes);
            } catch (NoSuchMethodException ex) {
                if (clazz.getSuperclass() == null) {
                    return method;
                } else {
                    method = getMethod(clazz.getSuperclass(), methodName,
                            classes);
                }
            }
        }
        return method;
    }


}
