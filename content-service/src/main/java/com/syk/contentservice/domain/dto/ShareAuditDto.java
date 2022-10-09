package com.syk.contentservice.domain.dto;


import com.syk.contentservice.domain.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 沈永康
 * @Date 2022/10/4 15:35
 * @Version 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareAuditDto {
    private Integer id;
    private AuditStatusEnum auditStatus;
    private String reason;
    private Boolean showFlag;
}
