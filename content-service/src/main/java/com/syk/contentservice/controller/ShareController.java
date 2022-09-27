package com.syk.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;

import com.syk.contentservice.common.ResponseResult;
import com.syk.contentservice.common.ResultCode;
import com.syk.contentservice.domain.dto.ShareDto;
import com.syk.contentservice.domain.entity.Share;
import com.syk.contentservice.domain.entity.User;
import com.syk.contentservice.openfeign.UserService;
import com.syk.contentservice.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequestMapping(value = "/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {

    private final ShareService shareService;
    private final UserService userService;

    @GetMapping("{id}")
    @SentinelResource(value = "getShareById")
    public ResponseResult getShareById(@PathVariable Integer id) {
        String result = shareService.getNumber(2055);
        log.info(result);
        if("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
//        System.out.println(user);
        ShareDto shareDto = ShareDto.builder().share(share).nickName(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDto);
    }

    @GetMapping(value = "/all")
//    @SentinelResource(value = "getAllShares",blockHandler = "getAllSharesBlock")
    @SentinelResource(value = "getAllShares")
    public ResponseResult getAllShares() {
        String result = shareService.getNumber(2022);
        log.info(result);
        if("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        List<Share> all = shareService.findAll();
//        System.out.println(all);
        return ResponseResult.success(all);
    }

    public  ResponseResult getAllSharesBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }
}
