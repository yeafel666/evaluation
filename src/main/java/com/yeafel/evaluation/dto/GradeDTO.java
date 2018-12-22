package com.yeafel.evaluation.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 *    老师的评教成绩
 * Created by kangyifan on 2018/10/18 20:54
 */
@Data
public class GradeDTO {

    private Long gradeId;

    /** 教师编号 */
    private Long teacherId;

    private String teacherName;

    /** 课程id. */
    private Long courseId;

    private String courseName;

    /** 学生打分（平均分.*/
    private BigDecimal studentScore = new BigDecimal(0);        //默认值为0

    /**  老师打分（平均分）. */
    private BigDecimal teacherScore = new BigDecimal(0);        //默认值为0

    /** 教研室打分（平均分）.*/
    private BigDecimal researchScore = new BigDecimal(0);        //默认值为0

    /** 系部打分（平均分）. */
    private BigDecimal departmentScore = new BigDecimal(0);        //默认值为0

    /** 老师所得总分数（平均分）. */
    private BigDecimal totalScore = new BigDecimal(0);        //默认值为0
}
