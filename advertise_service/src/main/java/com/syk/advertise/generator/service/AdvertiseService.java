package com.syk.advertise.generator.service;

import com.syk.advertise.generator.domain.Advertise;

import java.util.List;


/**
* @author sunshine
* @description 针对表【advertise】的数据库操作Service
* @createDate 2022-09-24 17:53:35
*/
public interface AdvertiseService  {

    List<Advertise> getAdvertiseAll();

}
