package com.yeafel.evaluation.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  用户信息
 * Created by kangyifan on 2018/9/13 15:27
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 8581889082850103196L;

    private Integer id;

    /**  人员编号. */
    private String number;

    /**  密码. */
    private String password;

    /** 身份. */
    private String identity;

    /** 姓名. */
    private String name;

    /** 院系编号. */
    private String departmentNo;

    /** 班级编号. */
    private String clazzNo;
}
