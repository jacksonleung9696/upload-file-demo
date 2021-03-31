package com.demo.test.utils;

import java.util.UUID;

/**
 * @author lianghuayue
 * @Date 2021/4/1 1:03
 * @Version 1.0
 */

public class UniqueIdUtils {
    public static String createUUID(int len){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        uuid = uuid.substring(0, len);
        return uuid;
    }

    public static String create10UUID(){
        return createUUID(10);
    }
}
