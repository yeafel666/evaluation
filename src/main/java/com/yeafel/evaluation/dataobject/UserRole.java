package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户——角色表（user_role）
 * 备注:此表阐述用户与角色的关系，也就是说用户是什么角色。（这样设计的目的是因为一个用户可以是多个角色）
 * Created by kangyifan on 2018/9/13 16:46
 */
@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    /** 用户id */
    private Long userId;

    /** 角色id */
    private Long roleId;



}
