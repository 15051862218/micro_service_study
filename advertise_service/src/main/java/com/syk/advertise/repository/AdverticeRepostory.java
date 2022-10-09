package com.syk.advertise.repository;



import com.syk.advertise.generator.domain.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author 沈永康
 * @Date 2022/9/23 9:13
 * @Version 1.0
 */


public interface AdverticeRepostory extends JpaRepository<Advertise, Integer> {

}
