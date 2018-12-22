package com.yeafel.evaluation.enums;

import lombok.Getter;

/**
 * Created by kangyifan on 2018/9/17 15:11
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    LOGIN_FAIL(10,"登录失败,登录信息不正确"),

    USER_NOT_EXIST(11,"用户不存在"),

    PASSWORD_ERROR(12,"密码错误"),

    ROLE_REJECT(13,"你不拥有此角色"),

    ROLE_NOT_EXIST(14,"角色不存在"),

    UPDATE_ERROR(15,"更新失败"),

    CREATE_ERROR(16,"创建失败"),

    COURSE_NOT_EXIST(17,"课程不存在"),


    DEPARTMENT_NOT_EXIST(18,"部门不存在"),

    CLAZZ_NOT_EXIST(19,"班级不存在"),

    ACTIONROLE_NOT_EXIST(20,"角色权限不足"),

    INDEX_NOT_EXIST(21,"指标不存在"),


    OPTION_NOT_EXIST(22,"选项不存在"),


    SEMESTER_NOT_EXIST(23,"学期不存在"),


    GRADE_NOT_EXIST(24,"成绩不存在"),

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
