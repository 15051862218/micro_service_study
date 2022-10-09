package com.syk.userservice.rocketmq;


import com.syk.userservice.domain.dto.UserAddBonusDto;
import com.syk.userservice.domain.entity.BonusEventLog;
import com.syk.userservice.domain.entity.User;
import com.syk.userservice.repository.BonusEventLogRepository;
import com.syk.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 沈永康
 * @Date 2022/10/6 9:56
 * @Version 1.0
 */

@Service
@RocketMQMessageListener(consumerGroup = "consumer",topic = "add-bonus-of-syk")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusDto> {
    private final UserRepository userRepository;
    private final BonusEventLogRepository bonusEventLogRepository;
    @Override
    public void onMessage(UserAddBonusDto userAddBonusDto) {
            Integer userId = userAddBonusDto.getUserId();
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setBonus(user.getBonus()+userAddBonusDto.getBonus());
            userRepository.saveAndFlush(user);
        }
        bonusEventLogRepository.save(BonusEventLog.builder().userId(userId).value(userAddBonusDto.getBonus()).event("CONTRIBUTE").createTime(new Date()).description("投稿加分").build());
    }
}
