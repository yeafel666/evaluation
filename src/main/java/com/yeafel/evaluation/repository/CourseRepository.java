package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseRepository extends JpaRepository<Course,Long> {

    /** 根据id查询用户. */
    Course findByCourseId(Long CourseId);

    @Transactional
    @Query(value = "select * from course where if(?1 !='',course_name=?1,1=1)",nativeQuery = true)
    Page<Course> findCourseIfCourseNameIsNotNull(String courseName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from course where if(?1 !='',course_name=?1,1=1)",nativeQuery = true)
    Integer countCourseForPage(String courseName);

    void deleteByCourseId(Long courseId);

    Course findByCourseName(String courseName);
}
