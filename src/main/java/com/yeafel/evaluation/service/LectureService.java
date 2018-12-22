package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dto.LectureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *  授课服务层
 * Created by kangyifan on 2018/10/10 10:48
 */
public interface LectureService {


    Page<LectureDTO> findLecturesIfTeacherIdIsNotNull(Long teacherId, Pageable pageable);

    Integer countLectureForPage(Long teacherId);

    LectureDTO findByLectureId(Long lectureId);

    LectureDTO update(LectureDTO lectureDTO);

    LectureDTO create(LectureDTO lectureDTO);

    void delete(Long lectureId);
}
