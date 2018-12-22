package com.yeafel.evaluation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dataobject.Grade;
import com.yeafel.evaluation.dto.GradeDTO;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.repository.GradeRepository;
import com.yeafel.evaluation.service.GradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.evaluation.converter.Grade2GradeDTO.convert;

/**
 * Created by kangyifan on 2018/10/18 21:00
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public GradeDTO findByTeacherId(EvaluateInfo evaluateInfo) {
        GradeDTO gradeDTO = new GradeDTO();

        Grade result = gradeRepository.findByTeacherId(evaluateInfo.getTeacherId());
        if (result != null){
            gradeDTO = convert(result);
        }

        return gradeDTO;
    }

    @Override
    public GradeDTO save(GradeDTO gradeDTO) {
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeDTO,grade);
        return convert(gradeRepository.save(grade));
    }

    @Override
    public GradeDTO create(GradeDTO gradeDTO) {

        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeDTO,grade);
        Grade g = gradeRepository.save(grade);
        return convert(g);

    }

    @Override
    public GradeDTO update(GradeDTO gradeDTO) {
         Grade grade;
        if (gradeDTO.getGradeId() != null){
            grade = gradeRepository.findByGradeId(gradeDTO.getGradeId());
        }else {
            throw new EvaluationException(ResultEnum.GRADE_NOT_EXIST);
        }

        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(gradeDTO,grade,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        Grade g = gradeRepository.save(grade);
        return convert(g);
    }

    @Override
    public Page<GradeDTO> findIfTeacherId(Long teacherId, Pageable pageable) {
        Page<Grade> gradePage = gradeRepository.findIfTeacherId(teacherId,pageable);
        List<GradeDTO> gradeDTOList = convert(gradePage.getContent());
        Page<GradeDTO> gradeDTOPage = new PageImpl<GradeDTO>(gradeDTOList,pageable,gradePage.getTotalElements());
        return gradeDTOPage;
    }

    @Override
    public Integer countIfTeacherId(Long teacherId) {
        Integer count = gradeRepository.countIfTeacherId(teacherId);
        return count;
    }

}
