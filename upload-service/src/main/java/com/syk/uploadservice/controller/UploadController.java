package com.syk.uploadservice.controller;



import ch.qos.logback.core.util.FileUtil;
import com.syk.uploadservice.service.ISysFileService;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 沈永康
 * @Date 2022/9/27 21:25
 * @Version 1.0
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

        @Autowired
        private ISysFileService sysFileService;

        /**
         * 文件上传请求
         */
        @PostMapping("upload")
        public Map<String, Object> upload(MultipartFile file)
        {
            Map<String, Object> map = new HashMap<>();
            try
            {
                // 上传并返回访问地址
                String url = sysFileService.uploadFile(file);
                map.put("status", "success");
                map.put("data", url);
                return map;
            }
            catch (Exception e)
            {
                map.put("status", "error");
                map.put("msg", e.getMessage());
                return map;
            }
        }

}


