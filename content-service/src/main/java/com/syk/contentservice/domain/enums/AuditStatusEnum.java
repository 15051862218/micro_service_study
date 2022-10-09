package com.syk.contentservice.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 沈永康
 * @Date 2022/10/4 18:43
 * @Version 1.0
 */

@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     *  待审核
     */
    NOT_YET,
    /**
     *  通过
     */
    PASS,
    /**
     *  拒绝
     */
    REJECT
}