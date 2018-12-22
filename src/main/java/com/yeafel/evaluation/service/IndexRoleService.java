package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.IndexRole;

import java.util.List;

/**
 *  指标--角色表
 * Created by kangyifan on 2018/10/15 15:13
 */
public interface IndexRoleService {

    List<IndexRole> findIndexRolesByRoleId(Long roleId);
}
