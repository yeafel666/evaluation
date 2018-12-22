package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.ActionRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *   功能---角色  关系表
 * Created by kangyifan on 2018/9/25 11:03
 */
public interface ActionRoleRepository extends JpaRepository<ActionRole,Long> {

    /** 查询某个角色拥有哪些功能. */
    List<ActionRole> findActionRolesByRoleId(Long roleId);


    /** 根据id查询某条权限数据 .*/
    ActionRole findByActionRoleId(Long actionRoleId);


    /** 根据角色查看某个角色的权限(可为空) */
/*    @Transactional
    @Query(value = "select * from action_role  where if(?1 !='',role_id=?1,1=1) group by role_id",nativeQuery = true)
    Page<ActionRole> findRoleActionIfRoleNameIsNotNull(Long roleId, Pageable pageable);*/



    /** 根据角色查看某个角色的权限(可为空)   无参*/
    @Transactional
    @Query(value = "select * from action_role group by role_id",nativeQuery = true)
    Page<ActionRole> findRoleActionIfRoleNameIsNotNull(Pageable pageable);




    /** 查询权限记录总数 .*/
/*    @Transactional
    @Query(value = "select count(*) from action_role  where if(?1 !='',role_id=?1,1=1) group by role_id",nativeQuery = true)
    Integer countActionRoleForPage(Long roleId);*/

    /** 查询权限记录总数 .      无参函数*/
    @Transactional
    @Query(value = "select count(distinct role_id) from action_role",nativeQuery = true)
    Integer countAllByActionRoleIdIsNotNull();


    /** 根据功能id和角色id联合删记录 .*/
    void deleteByActionIdAndRoleId(Long actionId,Long roleId);



    /** 取消某个角色的所有权限 */
    void deleteActionRolesByRoleId(Long roleId);





}
