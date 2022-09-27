package com.syk.contentservice.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.syk.contentservice.domain.entity.Share;

import java.util.List;

/**
 * @description:
 * @author: mth
 * @date: 2022/9/6
 **/
public interface ShareService {
    /**
     * 根据id查找分享
     *
     * @param id id
     * @return 分享内容
     */
    Share findById(Integer id);

    /**
     * 获取所有share
     * @return 所有share
     */
    List<Share> findAll();

    String getNumber(int number);

    String blockHandlerGetNumber(int number, BlockException e);
}
