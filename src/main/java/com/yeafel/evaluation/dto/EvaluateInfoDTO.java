package com.yeafel.evaluation.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 *  评教记录
 * Created by kangyifan on 2018/10/16 14:35
 */
@Data
public class EvaluateInfoDTO {

    private Long evaluateInfoId;

    /**  评价人(对应用户表的user_id). */
    private Long evaluatorId;

    /** 评价方角色 .*/
    private Long roleId;

    /** 评价方角色 .*/
    private String roleName;

    /** 教师Id(对应用户表的user_id). */
    private Long teacherId;

    /** 评价的课程id .*/
    private Long courseId;

    /** 评分数.*/
    private BigDecimal score;

    /** 评价人姓名. */
    private String evaluatorName;

    /** 教师姓名 .*/
    private String teacherName;


    /** 课程名. */
    private String courseName;


}
