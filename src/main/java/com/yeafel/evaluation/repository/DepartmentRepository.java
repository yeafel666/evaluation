package com.yeafel.evaluation.repository;


import com.yeafel.evaluation.dataobject.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Department findByDepartmentId(Long DepartmentId);
    @Transactional
    @Query(value = "select * from department where if(?1 !='',department_name=?1,1=1)",nativeQuery = true)
    public Page<Department> findDepartmentIfDepartmentNameIsNotNull(String departmentName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from department where if(?1 !='',department_name=?1,1=1)",nativeQuery = true)
    public Integer countDepartmentForPage(String departmentName);

    void deleteByDepartmentId(Long departmentId);


    Department findByDepartmentNo(String departmentNo);


    Department findByDepartmentName(String departmentName);


}
