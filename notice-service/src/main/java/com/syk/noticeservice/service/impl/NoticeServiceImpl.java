package com.syk.noticeservice.service.impl;

import com.syk.noticeservice.domain.entity.Notice;
import com.syk.noticeservice.repository.NoticeRepostory;
import com.syk.noticeservice.service.NoticeService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sunshine
* @description 针对表【notice】的数据库操作Service实现
* @createDate 2022-09-27 15:35:16
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl  implements NoticeService {
     private final NoticeRepostory noticeRepostory;
    @Override
    public List<Notice> getAllnotice() {
        return noticeRepostory.findAll();
    }

    @Override
    public Notice getLatestNotice() {
        //ascending()升序，descending()降序
        Sort sort = Sort.by("createTime").descending();
        List<Notice> noticeList = noticeRepostory.findByShowFlag(true, sort);
        if (!noticeList.isEmpty()) {
            return noticeList.get(0);
        } else {
            return null;
        }
    }
}
