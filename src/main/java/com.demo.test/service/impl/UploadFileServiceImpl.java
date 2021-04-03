package com.demo.test.service.impl;

import com.demo.test.dto.Result;
import com.demo.test.mapper.TestFileMapper;
import com.demo.test.model.TestFile;
import com.demo.test.service.UploadFileService;
import com.demo.test.utils.ConvertObjType;
import com.demo.test.utils.FilePathUtils;
import com.demo.test.utils.UniqueIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private TestFileMapper fileMapper;

    @Override
    public Result upload(int fileType, MultipartFile multipartFile, String url) {
        String uuid = UniqueIdUtils.create10UUID();
        Map<String, Object> map = new HashMap<>(16);
        LocalDateTime startTime = LocalDateTime.now();
        if (url.length() == 0) {
            LOGGER.info("url is empty, it will upload by multipart");
            map = uploadByMultipart(multipartFile, uuid);
        } else {
            LOGGER.info("url exist, it will download by url");
        }
        LocalDateTime endTime = LocalDateTime.now();
        float runTime = startTime.until(endTime, ChronoUnit.MILLIS);
        String fileName = ConvertObjType.convert(map.get("fileName"));
        String destName = ConvertObjType.convert(map.get("destName"));
        TestFile file = new TestFile();
        file.setFileId(uuid);
        file.setFileName(fileName);
        file.setFilePath(destName);
        file.setFileType(fileType);
        file.setRunTime(runTime);
        file.setEndTime(endTime);
        file.setUploadTime(startTime);
        fileMapper.addFile(file);
        return new Result("success", uuid);
    }

    private Map<String, Object> uploadByMultipart(MultipartFile multipartFile, String uuid){
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
        Map<String, Object> map = new HashMap<>(16);
        map.put("fileName", fileName);
        map.put("destName", destName);
        return map;
    }

    private void uploadByUrl(String url, String uuid){

    }
}
