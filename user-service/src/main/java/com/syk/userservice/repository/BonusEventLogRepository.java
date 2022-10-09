package com.syk.userservice.repository;


import com.syk.userservice.domain.entity.BonusEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author 沈永康
 * @Date 2022/10/6 10:03
 * @Version 1.0
 */


public interface BonusEventLogRepository extends JpaRepository<BonusEventLog,Integer> {
}
