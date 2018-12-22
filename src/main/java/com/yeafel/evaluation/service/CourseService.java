package com.yeafel.evaluation.service;


import com.yeafel.evaluation.dataobject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface CourseService {

    Course create(Course course);

    Course updateCourse(Course course);

    /** layui查询结果 .*/
    public Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable);


    /** 查询总数，可传入名称 .*/
    public Integer countCourseForPage(String courseName);

    public Course findByCourseId(Long courseId);

    /** 删除课程 */
    void deleteByCourseId(Long courseId);
}
