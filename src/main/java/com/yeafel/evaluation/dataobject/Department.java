package com.yeafel.evaluation.dataobject;

/**
 * Created by kangyifan on 2018/9/13 10:51
 */

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/** 用来区分各个用户所属的不同院系  */
@Entity
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    /** 院系编号 */
    private String departmentNo;

    /** 院系名称. */
    private String departmentName;


}
