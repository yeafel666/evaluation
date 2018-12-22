package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.EvaluateInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  评教记录表
 * Created by kangyifan on 2018/10/16 12:03
 */
public interface EvaluateInfoRepository extends JpaRepository<EvaluateInfo,Long> {

    /** 查询某个人的评教记录，可以对老师进行条件查询 .*/
    @Transactional
    @Query(value = "select * from evaluate_info where if(?1 !='',teacher_id=?1,1=1) and evaluator_id=?2",nativeQuery = true)
    Page<EvaluateInfo> findEvaluateInfosByEvaluatorId(Long teacherId,Long evaluatorId,Pageable pageable);


    /** 查询某个人的评教记录，可以对老师进行条件查询 .(对上面的总数进行计算)*/
    @Transactional
    @Query(value = "select count(*) from evaluate_info where if(?1 !='',teacher_id=?1,1=1) and evaluator_id=?2",nativeQuery = true)
    Integer countForPageByEvaluatorId(Long teacherId,Long evaluatorId);

    /** 根据老师id 得到该老师所有被评价的单条记录  .*/
    List<EvaluateInfo> findEvaluateInfosByTeacherId(Long teacherId);
}
