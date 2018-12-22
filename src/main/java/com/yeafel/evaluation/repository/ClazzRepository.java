package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Clazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ClazzRepository extends JpaRepository<Clazz,Long> {
    /** 根据id查询用户. */
    Clazz findByClazzId(Long ClazzId);

    @Transactional
    @Query(value = "select * from clazz where if(?1 !='',clazz_name=?1,1=1)",nativeQuery = true)
    Page<Clazz> findClazzIfClazzNameIsNotNull(String clazzName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from clazz where if(?1 !='',clazz_name=?1,1=1)",nativeQuery = true)
    Integer countClazzForPage(String clazzName);

    void deleteByClazzId(Long clazzId);

    Clazz findByClazzNo(String clazzNo);
}
