package com.yeafel.evaluation.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.yeafel.evaluation.dataobject.Lecture;
import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.LectureDTO;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.repository.*;
import com.yeafel.evaluation.service.LectureService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yeafel.evaluation.converter.Lecture2LectureDTOConverter.convert;

/**
 * Created by kangyifan on 2018/10/10 10:51
 */
@Service
@Transactional
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;


    @Autowired
    private DepartmentRepository departmentRepository;


    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ClazzRepository clazzRepository;


    @Override
    public Page<LectureDTO> findLecturesIfTeacherIdIsNotNull(Long teacherId, Pageable pageable) {

        Page<Lecture> lecturePage = lectureRepository.findLecturesIfTeacherIdIsNotNull(teacherId,pageable);

        List<LectureDTO> lectureDTOList = convert(lecturePage.getContent());

        for (LectureDTO lectureDTO : lectureDTOList){

            User teacher = userRepository.findByUserId(lectureDTO.getTeacherId());
            lectureDTO.setTeacherName(teacher.getUsername());
            lectureDTO.setTeacherNo(teacher.getUserNo());
            lectureDTO.setClazzNo(clazzRepository.findByClazzId(lectureDTO.getClazzId()).getClazzNo());
            lectureDTO.setCourseName(courseRepository.findByCourseId(lectureDTO.getCourseId()).getCourseName());
            lectureDTO.setDepartmentName(departmentRepository.findByDepartmentId(lectureDTO.getDepartmentId()).getDepartmentName());
            lectureDTO.setSemesterName(semesterRepository.findBySemesterId(lectureDTO.getSemesterId()).getSemesterName());
        }

        Page<LectureDTO> lectureDTOPage = new PageImpl<LectureDTO>(lectureDTOList,pageable,lecturePage.getTotalElements());

        return lectureDTOPage;
    }

    @Override
    public Integer countLectureForPage(Long teacherId) {
        Integer count = lectureRepository.countLectureForPage(teacherId);
        return count;
    }

    @Override
    public LectureDTO findByLectureId(Long lectureId) {
        Lecture lecture = lectureRepository.findByLectureId(lectureId);
        LectureDTO lectureDTO = convert(lecture);
        User teacher = userRepository.findByUserId(lecture.getTeacherId());

        lectureDTO.setTeacherNo(teacher.getUserNo());
        lectureDTO.setClazzNo(teacher.getClazzNo());
        lectureDTO.setCourseName(courseRepository.findByCourseId(lecture.getCourseId()).getCourseName());
        lectureDTO.setDepartmentName(departmentRepository.findByDepartmentId(lecture.getDepartmentId()).getDepartmentName());
        lectureDTO.setSemesterName(semesterRepository.findBySemesterId(lecture.getSemesterId()).getSemesterName());

        return lectureDTO;
    }

    @Override
    public LectureDTO update(LectureDTO lectureDTO) {
        Lecture lecture;
        if (lectureDTO.getLectureId() != null){
            lecture = lectureRepository.findByLectureId(lectureDTO.getLectureId());
        }else {
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        /** 把用户表里面相应的用户的班级编号进行修改 .*/
        Long TeacherId = lectureRepository.findByLectureId(lectureDTO.getLectureId()).getTeacherId();
        User teacher = userRepository.findByUserId(TeacherId);
        teacher.setClazzNo(lectureDTO.getClazzNo());
        userRepository.save(teacher);


        lectureDTO.setTeacherId(userRepository.findByUserNo(lectureDTO.getTeacherNo()).getUserId());
        lectureDTO.setClazzId(clazzRepository.findByClazzNo(lectureDTO.getClazzNo()).getClazzId());
        lectureDTO.setCourseId(courseRepository.findByCourseName(lectureDTO.getCourseName()).getCourseId());
        lectureDTO.setDepartmentId(departmentRepository.findByDepartmentName(lectureDTO.getDepartmentName()).getDepartmentId());
        lectureDTO.setSemesterId(semesterRepository.findBySemesterName(lectureDTO.getSemesterName()).getSemesterId());
        /**
         *      属性拷贝，从src到target。  倘若src里面有空值，则不拷贝过去
         */
        BeanUtil.copyProperties(lectureDTO,lecture,true, CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true));
        Lecture lecture1 = lectureRepository.save(lecture);

        /**
         * 这里就不需要把更新之后的lectureDto里面的所有值都填充好，无意义。
         */
        return convert(lecture1);
    }

    @Override
    public LectureDTO create(LectureDTO lectureDTO) {

        lectureDTO.setTeacherId(userRepository.findByUserNo(lectureDTO.getTeacherNo()).getUserId());
        lectureDTO.setClazzId(clazzRepository.findByClazzNo(lectureDTO.getClazzNo()).getClazzId());
        lectureDTO.setCourseId(courseRepository.findByCourseName(lectureDTO.getCourseName()).getCourseId());
        lectureDTO.setDepartmentId(departmentRepository.findByDepartmentName(lectureDTO.getDepartmentName()).getDepartmentId());
        lectureDTO.setSemesterId(semesterRepository.findBySemesterName(lectureDTO.getSemesterName()).getSemesterId());

        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(lectureDTO,lecture);
        Lecture lecture1 = lectureRepository.save(lecture);

        /**
         * 这里就不需要把更新之后的lectureDto里面的所有值都填充好，无意义。
         */
        return convert(lecture1);
    }

    @Override
    public void delete(Long lectureId) {
        lectureRepository.deleteByLectureId(lectureId);
    }
}
