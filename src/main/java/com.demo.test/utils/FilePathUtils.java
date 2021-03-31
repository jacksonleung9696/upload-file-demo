package com.demo.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author lianghuayue
 * @Date 2021/3/31 1:32
 * @Version 1.0
 */

public class FilePathUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilePathUtils.class);

    public static void createFilePath(File file) {
        if (file.getParent().isEmpty()) {
            LOGGER.info("parent path is empty, creating...");
            boolean flag = file.mkdirs();
            if (flag) {
                LOGGER.info("file path [{}] create success", file.getParent());
            }
        } else {
            file.delete();
            file.mkdirs();
        }
    }
}
