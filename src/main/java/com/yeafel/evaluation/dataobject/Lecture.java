package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *  授课实体
 * Created by kangyifan on 2018/10/10 9:40
 */

@Data
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;         //授课id

    private Long teacherId;    //老师的id，解法有多种，可以从用户表里动态加载班级为0的用户（班级为0代表老师）

    private Long clazzId;      //班级id

    private Long courseId;      //课程id

    private Long departmentId;  //院系id

    private Long semesterId;   //学期id




}
