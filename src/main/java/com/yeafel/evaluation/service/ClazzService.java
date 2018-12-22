package com.yeafel.evaluation.service;


import com.yeafel.evaluation.dataobject.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClazzService {
    Clazz create(Clazz clazz);

    Clazz updateClazz(Clazz clazz);

    /** layui查询结果 .*/
     Page<Clazz> findClazzIfClazzNameIsNotNull(String clazzName, Pageable pageable);


    /** 查询总数，可传入名称 .*/
     Integer countClazzForPage(String clazzName);

     Clazz findByClazzId(Long clazzId);

    /** 删除课程 */
    void deleteByClazzId(Long clazzId);
}
