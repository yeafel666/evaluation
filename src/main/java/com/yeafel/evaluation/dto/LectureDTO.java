package com.yeafel.evaluation.dto;

import lombok.Data;

/**
 * Created by kangyifan on 2018/10/10 10:35
 */
@Data
public class LectureDTO {

    private Long lectureId;         //授课id

    /** 老师 .*/
    private Long teacherId;    //老师的id，解法有多种，可以从用户表里动态加载班级为0的用户（班级为0代表老师）

    private String teacherNo;

    private String teacherName;


    /** 授课班级 .*/
    private Long clazzId;      //班级id

    private String clazzNo;


    /** 上什么课 .*/
    private Long courseId;      //课程id

    private String courseName;

    /** 老师属于哪个院系. */
    private Long departmentId;  //院系id

    private String departmentName;

    /** 学期id .*/
    private Long semesterId;   //学期id

    private String semesterName;   //学期id


    private Long isOpen;    //是否能够评教,0(不能)   1(能)

    private Long isMatch;       //是否匹配？ 0(不匹配)  1(匹配)

}
