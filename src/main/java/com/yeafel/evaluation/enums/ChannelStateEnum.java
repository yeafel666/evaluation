package com.yeafel.evaluation.enums;

import lombok.Getter;

/**
 * Created by kangyifan on 2018/9/13 11:39
 */
@Getter
public enum ChannelStateEnum implements CodeEnum<Integer>{
    NOSTART(0,"未开始"),
    START(1,"开始评教"),
    SUSPEND(2,"暂停评教"),
    STOP(3,"停止评教"),
    ;

    private Integer code;

    private String message;

    ChannelStateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
