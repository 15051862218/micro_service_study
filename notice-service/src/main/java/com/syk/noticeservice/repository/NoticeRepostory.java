package com.syk.noticeservice.repository;



import com.syk.noticeservice.domain.entity.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 沈永康
 * @Date 2022/9/23 9:13
 * @Version 1.0
 */


public interface NoticeRepostory extends JpaRepository<Notice, Integer> {
    List<Notice> findByShowFlag(Boolean showFlag, Sort sort);
}
