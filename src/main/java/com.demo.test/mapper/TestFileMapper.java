package com.demo.test.mapper;

import com.demo.test.model.TestFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lianghuayue
 * @Date 2021/4/1 0:57
 * @Version 1.0
 */
@Mapper
public interface TestFileMapper {
    /**
     * 把文件插入数据库
     *
     * @param testFile 文件对象
     * @return 返回操作数据库标志
     */
    Integer addFile(TestFile testFile);
}
