package com.syk.contentservice.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户-分享中间表【描述用户购买的分享】
 * @TableName mid_user_share
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MidUserShare  {
    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * share.id
     */
    private Integer shareId;

    /**
     * user.id
     */
    private Integer userId;




}