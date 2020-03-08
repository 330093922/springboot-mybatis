package com.thundersdata.backend.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 拷贝工具类
 */
public class CopyUtils {
    /**
     * list深拷贝
     *
     * @param src 源list
     * @return 新的list
     */
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
