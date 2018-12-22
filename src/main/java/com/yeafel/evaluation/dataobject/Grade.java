package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 评教成绩表
 * Created by kangyifan on 2018/9/13 11:46
 */
@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeId;

    /** 教师编号 */
    private Long teacherId;

    /** 课程id. */
    private Long courseId;

    /** 学生打分（平均分.*/
    private BigDecimal studentScore;

    /**  老师打分（平均分）. */
    private BigDecimal teacherScore;

    /** 教研室打分（平均分）.*/
    private BigDecimal researchScore;

    /** 系部打分（平均分）. */
    private BigDecimal departmentScore;

    /** 老师所得总分数（平均分）. */
    private BigDecimal totalScore;

}
