package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.GradeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *  教师成绩逻辑层
 * Created by kangyifan on 2018/10/18 20:58
 */
public interface GradeService {

    /** 根据教师id去拿到该教师的成绩记录 .   为什么要传EvaluateInfo对象？  看看service实现就知道了*/
    GradeDTO findByTeacherId(EvaluateInfo evaluateInfo);

    GradeDTO save(GradeDTO gradeDTO);

    GradeDTO create(GradeDTO gradeDTO);

    GradeDTO update(GradeDTO gradeDTO);

    /** 为了提供通过id进行条件查询并分页 。*/
    Page<GradeDTO> findIfTeacherId(Long teacherId, Pageable pageable);


    Integer countIfTeacherId(Long teacherId);
    /** 为了提供通过id进行条件查询并分页 。-----end*/
}
