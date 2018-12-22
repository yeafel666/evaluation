package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dto.ActionRoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  权限业务逻辑
 * Created by kangyifan on 2018/10/3 16:51
 */
public interface ActionRoleService {

    /** 根据角色id得到功能名称的数组 .*/
    List<String> findActionNamesByRoleId(Long roleId);


    /** 根据角色查看某个角色的权限(可为空) */
    Page<ActionRoleDTO> findRoleActionIfRoleNameIsNotNull(String roleName, Pageable pageable);


    /** 查询权限记录总数 .*/
    Integer countActionRoleForPage(String roleName);


    /** 更新角色权限表 .*/
    ActionRoleDTO update(ActionRoleDTO actionRoleDTO);


    /** 添加角色权限 .*/
    ActionRoleDTO create(ActionRoleDTO actionRoleDTO);


    ActionRoleDTO findByActionRoleId(Long actionRoleId);


    /** 取消某个角色的所有权限 */
    void deleteActionRolesByRoleId(Long roleId);
}
