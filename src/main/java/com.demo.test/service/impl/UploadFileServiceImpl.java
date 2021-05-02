package com.demo.test.service.impl;

import com.demo.test.dto.Result;
import com.demo.test.mapper.TestFileMapper;
import com.demo.test.model.TestFile;
import com.demo.test.service.UploadFileService;
import com.demo.test.utils.ConvertObjType;
import com.demo.test.utils.FilePathUtils;
import com.demo.test.utils.UniqueIdUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
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

    @Resource
    private RestTemplate restTemplate;

    @Override
    public Result upload(int fileType, MultipartFile multipartFile, String url) throws Exception {
        String uuid = UniqueIdUtils.create10UUID();
        Map<String, Object> map = new HashMap<>(16);
        LocalDateTime startTime = LocalDateTime.now();
        if (url.length() == 0) {
            LOGGER.info("url is empty, it will upload by multipart");
            map = uploadByMultipart(multipartFile, uuid);
            if (map.containsKey("fileSize") && ConvertObjType.convert(map.get("fileSize")).equals("large")) {
                return Result.fail("file size is too large");
            }
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

    private Map<String, Object> uploadByMultipart(MultipartFile multipartFile, String uuid) throws Exception {
        String fileName = multipartFile.getOriginalFilename();
        Integer index = fileName.lastIndexOf(".");
        String destName = rootDir + "/" + uuid + "/" + fileName;
        File file = new File(destName);
        Map<String, Object> map = new HashMap<>(16);
        if(!judgeFileSize(multipartFile)) {
            map.put("fileSize", "large");
            return map;
        }
        FilePathUtils.createFilePath(file);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            LOGGER.error("file transfer occur error: {}", e);
        }

        map.put("fileName", fileName);
        map.put("destName", destName);
        return map;
    }

    private void uploadByUrl(String url, String uuid) {
        byte[] bytes = restTemplate.getForObject(url, byte[].class);

    }

    public Boolean judgeFileSize(MultipartFile file) throws Exception {
        Boolean flag = true;
        LOGGER.info("file.getSize: {}", file.getSize());
        int fileS = file.getBytes().length;
        LOGGER.info("fileS: {}", fileS);
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileS < 1024*1024*1024) {
            String size = df.format((double) fileS / 1048576);
            LOGGER.info("size: {}", size);
            // Integer intSize = Integer;
            if (size != null) {
                Double intSize = Double.parseDouble(size.trim());
                //获取配置文件中的文件最大限制
                if (intSize > 1024*6) {  // 不能上传超过10M的文件!
                    flag = false;
                }
            }
        }
        return flag;
    }
}
