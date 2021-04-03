package com.demo.test.utils;

/**
 * @author lianghuayue
 * @Date 2021/4/3 14:40
 * @Version 1.0
 */

public class ConvertObjType {
    public static <T, E> T convert(E e){
        return (T) e;
    }
}
