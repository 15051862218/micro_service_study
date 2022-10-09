package com.syk.contentservice.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 沈永康
 * @Date 2022/10/6 9:42
 * @Version 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDto {
    private Integer userId;
    private Integer bonus;
}
