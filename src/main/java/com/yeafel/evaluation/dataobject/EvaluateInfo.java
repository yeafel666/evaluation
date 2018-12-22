package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * EvaluateInfoService  （用来记录某个用户给某个老师的某门课程打了多少分）
 * 评分信息表
 * Created by kangyifan on 2018/9/13 11:29
 */
@Entity
@Data
public class EvaluateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluateInfoId;

    /**  评价人(对应用户表的user_id). */
    private Long evaluatorId;

    /** 评价方角色 .*/
    private Long roleId;

    /** 教师id(对应用户表的user_id). */
    private Long teacherId;

    /** 评价的课程 .*/
    private Long courseId;

    /** 评分数.*/
    private BigDecimal score;


}
