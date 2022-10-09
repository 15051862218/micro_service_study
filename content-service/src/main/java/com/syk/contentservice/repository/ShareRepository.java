package com.syk.contentservice.repository;


import com.syk.contentservice.domain.entity.Share;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface ShareRepository extends JpaRepository<Share, Integer> {

    Page<Share> findAllByAuditStatus(String  auditStatus,Pageable pageable);
}
