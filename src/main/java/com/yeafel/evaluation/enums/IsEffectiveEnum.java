package com.yeafel.evaluation.enums;

import lombok.Getter;

/**
 * Created by kangyifan on 2018/9/13 11:09
 */
@Getter
public enum IsEffectiveEnum implements CodeEnum<Integer>{
    VALID(0,"生效"),
    INVALID(1,"无效的"),
    ;

    private Integer code;

    private String message;

    IsEffectiveEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
