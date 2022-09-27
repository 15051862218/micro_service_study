package com.syk.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syk.contentservice.common.ResponseResult;
import com.syk.contentservice.common.ResultCode;
import com.syk.contentservice.domain.entity.Notice;
import com.syk.contentservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/notices")
@Slf4j
@RefreshScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;
    @Value("${disableNoticeRequest:false}")
     private  Boolean disableNoitice;
    @GetMapping("/latest")
    @SentinelResource(value = "getNotice",blockHandler = "getNoticeBlock")
    public ResponseResult getNotice() {
        Notice latestNotice = noticeService.getLatestNotice();
        if (disableNoitice) {
            log.info("暂停服务");
            return ResponseResult.failure(ResultCode.INTERFACE_ADDRESS_INVALID);
        } else {
            return ResponseResult.success(latestNotice);
        }
//        Notice latestNotice = noticeService.getLatestNotice();
//        if (latestNotice != null) {
//            return ResponseResult.success(latestNotice);
//        } else {
//            return ResponseResult.failure(ResultCode.NOTICE_NO_LATEST);
//        }
    }

 public  ResponseResult getNoticeBlock(BlockException exception) {
            log.info("接口被限流");
            log.info(exception.toString());
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
    }

