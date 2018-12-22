package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.Action;
import com.yeafel.evaluation.dataobject.Course;
import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.dataobject.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kangyifan on 2018/9/20 11:49
 */
public interface RoleService {

    /** 添加角色. */
    Role create(Role role);

    /** 根据id查询角色 .*/
    Role findByRoleId(Long roleId);

    /** 分页查看所有角色. */
    Page<Role> findAll(Pageable pageable);

    /** 查看某个用户是否拥有某个角色. */
    Boolean checkUserRole(Long userId,Long roleId);


    /** 查询某个角色拥有哪些功能. */
    List<Action> findActionsByRoleId(Long roleId);


    /** 给layui提供查询 */
    Page<Role> finRolesIfRolenameIsNotNull(String roleName, Pageable pageable);


    Integer countRoleForPage(String roleName);


    Role updateRole(Role role);


    void deleteByRoleId(Long roleId);


    /** 查询某个角色拥有的指标. */
    List<Index> findIndexesByRoleId(Long roleId);










}
