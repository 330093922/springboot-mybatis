package com.thundersdata.backend.common.utils;

import org.hashids.Hashids;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：Hashids工具
 */
public class HashidsUtils {
    private static String SALT = "thundersdata";

    /**
     * Hashids加密
     *
     * @param id 待加密ID
     * @return 加密Hash
     */
    public static String encode(Integer id) {
        Hashids hashids = new Hashids(SALT);
        return hashids.encode(Long.valueOf(id));
    }

    /**
     * Hashids解密
     *
     * @param hash 待解密Hash
     * @return 解密ID
     */
    public static Integer decode(String hash) {
        Hashids hashids = new Hashids(SALT);
        if (hash == null) {
            return null;
        }

        long[] numbers = hashids.decode(hash);
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        return new Long(numbers[0]).intValue();
    }

    /**
     * Hashids批量解密
     *
     * @param hashList 待解密Hash列表
     * @return 解密ID列表
     */
    public static List<Integer> batchDecode(List<String> hashList) {
        List<Integer> ids = new ArrayList<>();
        for (String hash : hashList) {
            ids.add(decode(hash));
        }

        return ids;
    }

}
