package com.syk.contentservice.domain.dto;


import com.syk.contentservice.domain.entity.Share;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareDto {
    private Share share;
    private String nickName;
    private String avatar;
}
