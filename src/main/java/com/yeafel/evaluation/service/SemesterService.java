package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SemesterService {
    Semester create(Semester semester);

    Semester updateSemester(Semester semester);

    /** layui查询结果 .*/
    Page<Semester> findSemesterIfSemesterNameIsNotNull(String semesterName, Pageable pageable);


    /** 查询总数，可传入名称 .*/
    Integer countSemesterForPage(String semesterName);

    Semester findBySemesterId(Long semesterId);

    /** 删除课程 */
    void deleteBySemesterId(Long semesterId);
}
