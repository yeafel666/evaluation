package com.yeafel.evaluation.repository;

import com.yeafel.evaluation.dataobject.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
    Semester findBySemesterId(Long SemesterId);

    @Transactional
    @Query(value = "select * from semester where if(?1 !='',semester_name=?1,1=1)",nativeQuery = true)
    public Page<Semester> findSemesterIfSemesterNameIsNotNull(String semesterName, Pageable pageable);


    @Transactional
    @Query(value = "select count(*) from semester where if(?1 !='',semester_name=?1,1=1)",nativeQuery = true)
    public Integer countSemesterForPage(String semesterName);

    void deleteBySemesterId(Long semesterId);

    Semester findBySemesterName(String semesterName);
}
