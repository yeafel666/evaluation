package com.yeafel.evaluation.service.impl;

import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.EvaluateInfoDTO;
import com.yeafel.evaluation.repository.CourseRepository;
import com.yeafel.evaluation.repository.EvaluateInfoRepository;
import com.yeafel.evaluation.repository.UserRepository;
import com.yeafel.evaluation.service.EvaluateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.evaluation.converter.EvaluateInfo2EvaluateInfoDTOConverter.convert;

/**
 *  评教记录
 * Created by kangyifan on 2018/10/16 12:09
 */
@Service
@Transactional
public class EvaluateInfoServiceImpl implements EvaluateInfoService {

    @Autowired
    private EvaluateInfoRepository evaluateInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EvaluateInfo save(EvaluateInfo evaluateInfo) {

        EvaluateInfo info = evaluateInfoRepository.save(evaluateInfo);

        return info;
    }

    @Override
    public Page<EvaluateInfoDTO> findEvaluateInfosByEvaluatorId(Long teacherId, Long evaluatorId, Pageable pageable) {
        Page<EvaluateInfo> evaluateInfoPage =evaluateInfoRepository.findEvaluateInfosByEvaluatorId(teacherId,evaluatorId,pageable);
        List<EvaluateInfoDTO> evaluateInfoDTOList = convert(evaluateInfoPage.getContent());
        for (EvaluateInfoDTO e : evaluateInfoDTOList){
            e.setEvaluatorName(userRepository.findByUserId(e.getEvaluatorId()).getUsername());
            e.setTeacherName(userRepository.findByUserId(e.getTeacherId()).getUsername());
            e.setCourseName(courseRepository.findByCourseId(e.getCourseId()).getCourseName());
        }
        Page<EvaluateInfoDTO> evaluateInfoDTOPage = new PageImpl<EvaluateInfoDTO>(evaluateInfoDTOList,pageable,evaluateInfoPage.getTotalElements());
        return evaluateInfoDTOPage;
    }

    @Override
    public Integer countForPageByEvaluatorId(Long teacherId, Long evaluatorId) {
        Integer count =  new Long(evaluateInfoRepository.countForPageByEvaluatorId(teacherId,evaluatorId)).intValue();
        return count;
    }

    @Override
    public List<EvaluateInfoDTO> findEvaluateInfosByTeacherId(Long teacherId) {
        List<EvaluateInfo> evaluateInfoList = evaluateInfoRepository.findEvaluateInfosByTeacherId(teacherId);
        return convert(evaluateInfoList);
    }
}
