package com.demo.test.service.impl;

import com.demo.test.dto.Result;
import com.demo.test.service.UploadFileService;
import com.demo.test.utils.FilePathUtils;
import com.demo.test.utils.UniqueIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author lianghuayue
 * @Date 2021/3/31 1:22
 * @Version 1.0
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    @Value("${demo.dir}")
    private String rootDir;

    @Override
    public Result upload(String fileType, MultipartFile multipartFile, String url) {
        String uuid = UniqueIdUtils.create10UUID();
        if (url.length() == 0) {
            LOGGER.info("url is empty, it will upload by multipart");
            uploadByMultipart(multipartFile, uuid);
        } else {
            LOGGER.info("url exist, it will download by url");
        }
        return new Result("success", uuid);
    }

    private void uploadByMultipart(MultipartFile multipartFile, String uuid){
        String fileName = multipartFile.getOriginalFilename();
        Integer index = fileName.lastIndexOf(".");
        String destName = rootDir + "/" + uuid + "/" + fileName;
        File file = new File(destName);
        FilePathUtils.createFilePath(file);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            LOGGER.error("file transfer occur error: {}", e);
        }
    }

    private void uploadByUrl(String url, String uuid){

    }
}
