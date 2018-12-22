package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户-院系 关系表（user_department）
 * 用来存储某个用户属于哪个院系。
 * Created by kangyifan on 2018/9/13 16:40
 */
@Data
@Entity
public class UserDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userDepartmentId;

    /** 用户人员编号 */
    private String userNo;

    /** 院系编号 */
    private String departmentNo;


}
