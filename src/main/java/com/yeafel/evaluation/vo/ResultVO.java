package com.yeafel.evaluation.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  http请求返回的最外层对象
 * Created by kangyifan on 2018/9/17 11:54
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -9115983210910663512L;

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 数据总数. */
    private Integer count;

    /** 具体内容. */
    private T data;

    /** 为老师自评服务. */
    private Long teacherId;

}
