package com.syk.contentservice.service.impl;


import com.syk.contentservice.domain.entity.Notice;
import com.syk.contentservice.repository.NoticeRepository;
import com.syk.contentservice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Notice getLatestNotice() {
        //ascending()升序，descending()降序
        Sort sort = Sort.by("createTime").descending();
        List<Notice> noticeList = noticeRepository.findByShowFlag(true, sort);
        if (!noticeList.isEmpty()) {
            return noticeList.get(0);
        } else {
            return null;
        }
    }

}
