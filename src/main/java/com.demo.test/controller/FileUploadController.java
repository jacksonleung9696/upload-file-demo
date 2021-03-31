package com.demo.test.controller;

import com.demo.test.dto.Result;
import com.demo.test.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author lianghuayue
 * @Date 2021/3/30 23:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/demo")
public class FileUploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

    @Resource
    private UploadFileService uploadFileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("fileType") String fileType, @RequestParam("file") MultipartFile multipartFile, @RequestParam("url") String url) {
        LOGGER.info("incoming params is: {}, {}, {}", fileType, multipartFile, url);
        return uploadFileService.upload(fileType, multipartFile, url);
    }
}
