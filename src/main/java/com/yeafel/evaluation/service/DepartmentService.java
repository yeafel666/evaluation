package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Department create(Department department);
    Department updateDepartment(Department department);

    Page<Department> findDepartmentIfDepartmentNameIsNotNull(String departmentName, Pageable pageable);
    Integer countDepartmentForPage(String departmentName);

    Department findByDepartmentId(Long departmentId);

    Department findByDepartmentNo(String departmentNo);

    /** 删除课程 */
    void deleteByDepartmentId(Long departmentId);
}
