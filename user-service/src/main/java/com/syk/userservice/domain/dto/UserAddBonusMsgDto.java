package com.syk.userservice.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;

/**
 * @Author 沈永康
 * @Date 2022/10/6 9:53
 * @Version 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusMsgDto {
    private Integer userId;
    private Integer bonus;
    private String description;
    private String event;
}
