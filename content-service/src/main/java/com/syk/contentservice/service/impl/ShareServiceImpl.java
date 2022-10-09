package com.syk.contentservice.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syk.contentservice.domain.dto.ShareAuditDto;
import com.syk.contentservice.domain.dto.UserAddBonusDto;
import com.syk.contentservice.domain.entity.MidUserShare;
import com.syk.contentservice.domain.entity.Share;
import com.syk.contentservice.domain.enums.AuditStatusEnum;
import com.syk.contentservice.repository.MidUserShareRepository;
import com.syk.contentservice.repository.ShareRepository;
import com.syk.contentservice.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Objects;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;
    private final RocketMQTemplate rocketMQTemplate;
    private final MidUserShareRepository midUserShareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Share> findAll(Integer page,Integer pageSize) {
        Sort sort = Sort.by("createTime").descending();
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        return shareRepository.findAllByAuditStatus("PASS",pageable);
    }

    @Override
    @SentinelResource(value = "getNumber",blockHandler = "blockHandlerGetNumber")
    public String getNumber(int number) {
        return "number  = { "+ number +"}";
    }

    @Override
    public String blockHandlerGetNumber(int number, BlockException e) {
        return "BLOCKED";
    }

    @Override
    public Share auditShare(ShareAuditDto shareAuditDto) {
         Share share = shareRepository.findById(shareAuditDto.getId()).orElse(null);
         if (!Objects.equals("NOT_YET",share.getAuditStatus())){
             throw new IllegalArgumentException("参数非法！该分享已审核");
         }
         share.setAuditStatus(shareAuditDto.getAuditStatus().toString());
         share.setReason(shareAuditDto.getReason());
         share.setShowFlag(shareAuditDto.getShowFlag());
         Share newShare =shareRepository.saveAndFlush(share);
         midUserShareRepository.save(MidUserShare.builder().shareId(newShare.getId()).userId(newShare.getUserId()).build());
         if (AuditStatusEnum.PASS.equals(shareAuditDto.getAuditStatus())){
             rocketMQTemplate.convertAndSend(
                     "add-bonus-of-syk",
                     UserAddBonusDto.builder().userId(share.getUserId()).bonus(50).build()
             );
         }
         return newShare;
    }
}
