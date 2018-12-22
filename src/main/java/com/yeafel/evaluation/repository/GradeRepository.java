package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kangyifan on 2018/10/18 20:52
 */
public interface GradeRepository extends JpaRepository<Grade,Long> {

    Grade findByTeacherId(Long teacherId);

    Grade findByGradeId(Long gradeId);

    /** 为了提供通过id进行条件查询并分页 。*/
    @Transactional
    @Query(value = "select * from grade where if(?1 !='',teacher_id=?1,1=1)",nativeQuery = true)
    Page<Grade> findIfTeacherId(Long teacherId, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from grade where if(?1 !='',teacher_id=?1,1=1)",nativeQuery = true)
    Integer countIfTeacherId(Long teacherId);
    /** 为了提供通过id进行条件查询并分页 。-----end*/

}
