package com.syk.contentservice.repository;


import com.syk.contentservice.domain.entity.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    /**
     * 根据类型提交查询，根据日期排序
     *
     * @param showFlag 是否显示
     * @param sort     排序条件
     * @return List<notice>
     */
    List<Notice> findByShowFlag(Boolean showFlag, Sort sort);
}
