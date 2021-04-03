package com.demo.test.service;

import com.demo.test.dto.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lianghuayue
 * @Date 2021/3/31 1:22
 * @Version 1.0
 */
public interface UploadFileService {
    /**
     * 上传文件service层
     *
     * @param fileType 文件类型
     * @param multipartFile 文件流
     * @param url 路径
     * @return 返回result对象
     */
    Result upload(int fileType, MultipartFile multipartFile, String url);
}
