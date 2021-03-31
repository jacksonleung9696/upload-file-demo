package com.demo.test.model;

import java.time.LocalDateTime;

/**
 * @author lianghuayue
 * @Date 2021/4/1 0:52
 * @Version 1.0
 */
public class TestFile {
    private String fileId;
    private String fileName;
    private Integer fileType;
    private String filePath;
    private LocalDateTime uploadTime;
    private LocalDateTime endTime;
    private float runTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getRunTime() {
        return runTime;
    }

    public void setRunTime(float runTime) {
        this.runTime = runTime;
    }

    @Override
    public String toString() {
        return "TestFile{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType=" + fileType +
                ", filePath='" + filePath + '\'' +
                ", uploadTime=" + uploadTime +
                ", endTime=" + endTime +
                ", runTime=" + runTime +
                '}';
    }
}
