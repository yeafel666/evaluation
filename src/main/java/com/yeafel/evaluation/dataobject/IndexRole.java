package com.yeafel.evaluation.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 指标——角色表(index_role)
 * Created by kangyifan on 2018/9/13 16:43
 */
@Entity
@Data
public class IndexRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long indexRoleId;

    /** 指标id */
    private Long indexId;

    /** 角色id */
    private Long roleId;
}
