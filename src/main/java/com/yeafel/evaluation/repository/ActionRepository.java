package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kangyifan on 2018/9/13 16:54
 */
public interface ActionRepository  extends JpaRepository<Action,Long> {



    /** 根据id查询功能 */
    Action findByActionId(Long actionId);

    /** 删除功能 .*/
    void deleteByActionId(Long actionId);

    @Transactional
    @Query(value = "select * from action where if(?1 !='',action_name=?1,1=1)",nativeQuery = true)
    Page<Action> findActionIfActionNameIsNotNull(String actionName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from action where if(?1 !='',action_name=?1,1=1)",nativeQuery = true)
     Integer countActionForPage(String actionName);





}
