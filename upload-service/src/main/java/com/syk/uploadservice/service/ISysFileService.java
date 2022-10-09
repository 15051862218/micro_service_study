package com.syk.uploadservice.service;


import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 沈永康
 * @Date 2022/9/27 21:22
 * @Version 1.0
 */


public interface ISysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    String uploadFile(MultipartFile file) throws Exception;
}
