package com.syk.noticeservice.domain.entity;

import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: Notice表实体类
 * @author: mth
 * @date: 2022/9/6
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否显示 0:否 1:是
     */
    private Boolean showFlag;

    /**
     * 创建时间
     */
    private Date createTime;

}