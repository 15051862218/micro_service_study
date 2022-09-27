package com.syk.noticeservice.service;

import java.util.List;

/**
* @author sunshine
* @description 针对表【notice】的数据库操作Service
* @createDate 2022-09-27 15:35:16
*/
public interface NoticeService <Notice> {
    List<Notice> getAllnotice();
    Notice getLatestNotice();
}
