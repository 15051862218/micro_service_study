package com.syk.noticeservice.controller;



import com.syk.noticeservice.common.ResponseResult;
import com.syk.noticeservice.common.ResultCode;
import com.syk.noticeservice.domain.entity.Notice;
import com.syk.noticeservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 沈永康
 * @Date 2022/9/24 18:03
 * @Version 1.0
 */

@RestController
@Slf4j
@RefreshScope
@RequestMapping(value = "/notices")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceController {
private final NoticeService noticeService;
    @Value("${disableNoticeRequest:false}")
    private  Boolean disableNoitice;
    @GetMapping("/latest")
//    @SentinelResource(value = "getNotice",blockHandler = "getNoticeBlock")
    public ResponseResult getNotice() {
        Notice latestNotice = (Notice) noticeService.getLatestNotice();
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


    @GetMapping("/all")
    public ResponseResult getAllAdvertise() {
        return ResponseResult.success(noticeService.getAllnotice());
    }
}
