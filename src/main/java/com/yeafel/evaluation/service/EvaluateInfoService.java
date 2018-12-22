package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.EvaluateInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kangyifan on 2018/10/16 12:06
 */
public interface EvaluateInfoService {

    EvaluateInfo save(EvaluateInfo evaluateInfo);

    /** 查询某个人的评教记录，可以对老师进行条件查询 .*/
    Page<EvaluateInfoDTO> findEvaluateInfosByEvaluatorId(Long teacherId, Long evaluatorId, Pageable pageable);

    /** 查询某个人的评教记录，可以对老师进行条件查询 .(对上面方法的总数进行计算) 配套使用*/
    Integer countForPageByEvaluatorId(Long teacherId,Long evaluatorId);


    List<EvaluateInfoDTO> findEvaluateInfosByTeacherId(Long teacherId);

}
