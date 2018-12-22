package com.yeafel.evaluation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yeafel.evaluation.enums.IdentityEnum;
import com.yeafel.evaluation.utils.EnumUtil;
import lombok.Data;

/**
 * Created by kangyifan on 2018/9/17 12:42
 */
@Data
public class UserDTO {


    /** 用户id. */
    private Long userId;

    /**  人员编号. */
    private String userNo;

    /**  密码. */
    private String password;

    /** 身份. */
    private Integer identity;

    /** 用户姓名. */
    private String username;

    /** 院系编号. */
    private String departmentNo;

    /** 班级编号. */
    private String clazzNo;


    /** 院系名称. */
    private String departmentName;


    @JsonIgnore //对象转换成json格式返回到前端会忽略该方法
    public IdentityEnum getIdentityEnum(){
        return EnumUtil.getByCode(identity,IdentityEnum.class);
    }


}
