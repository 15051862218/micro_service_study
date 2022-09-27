package com.syk.advertise.generator.service.impl;

import com.syk.advertise.common.ResponseResult;
import com.syk.advertise.generator.domain.Advertise;
import com.syk.advertise.generator.service.AdvertiseService;
import com.syk.advertise.repository.AdverticeRepostory;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sunshine
* @description 针对表【advertise】的数据库操作Service实现
* @createDate 2022-09-24 17:53:35
*/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdvertiseServiceImpl implements AdvertiseService{
    private  final AdverticeRepostory adverticeRepostory;

    @Override
    public List<Advertise> getAdvertiseAll() {
        return adverticeRepostory.findAll();
    }
}
