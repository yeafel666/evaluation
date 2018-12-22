package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 选项表：（Option）
 * Created by kangyifan on 2018/9/13 11:19
 */
@Entity
@Data
@Table(name = "[Option]")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    /** 指标id. */
    private Long indexId;


    /** 选项描述. */
    private String description;


    /** 选项的分数. */
    private BigDecimal optionScore;
}
