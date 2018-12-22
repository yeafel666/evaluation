package com.yeafel.evaluation.service.impl;


import com.yeafel.evaluation.dataobject.Course;
import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.repository.CourseRepository;
import com.yeafel.evaluation.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.yeafel.evaluation.converter.User2UserDTOConverter.convert;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Course create(Course course) {
        return  courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course result;
        if (course.getCourseId()!= null){
              result = courseRepository.save(course);
        }else {
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable) {

        Page<Course> coursePage = courseRepository.findCourseIfCourseNameIsNotNull(courseName,pageable);

        return coursePage;
    }

    @Override
    public Integer countCourseForPage(String courseName) {
        Integer count = courseRepository.countCourseForPage(courseName);

        return count;
    }

    @Override
    public Course findByCourseId(Long courseId) {

        Course course = courseRepository.findByCourseId(courseId);
        if (course == null){
            throw new EvaluationException(ResultEnum.USER_NOT_EXIST);
        }

        return course;
    }

    @Override
    public void deleteByCourseId(Long courseId) {
        courseRepository.deleteByCourseId(courseId);
    }
}
