package com.yeafel.evaluation.service.impl;


import com.yeafel.evaluation.dataobject.ChannelControl;
import com.yeafel.evaluation.dataobject.Semester;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;

import com.yeafel.evaluation.repository.ChannelControlRepository;
import com.yeafel.evaluation.repository.SemesterRepository;

import com.yeafel.evaluation.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Autowired
    private ChannelControlRepository channelControlRepository;

    @Override
    public Semester create(Semester semester) {

        Semester result = semesterRepository.save(semester);
        ChannelControl channelControl = new ChannelControl();
        channelControl.setSemesterId(result.getSemesterId());
        channelControl.setChannelState(0);
        channelControlRepository.save(channelControl);

        return result;
    }

    @Override
    public Semester updateSemester(Semester semester) {
        Semester result;
        if (semester.getSemesterId()!= null){
            result = semesterRepository.save(semester);
        }else {
            throw new EvaluationException(ResultEnum.SEMESTER_NOT_EXIST);
        }
        return  result;
    }

    @Override
    public Page<Semester> findSemesterIfSemesterNameIsNotNull(String semesterName, Pageable pageable) {
        Page<Semester> semesterPage = semesterRepository.findSemesterIfSemesterNameIsNotNull(semesterName,pageable);

        return semesterPage;
    }

    @Override
    public Integer countSemesterForPage(String semesterName) {
        Integer count = semesterRepository.countSemesterForPage(semesterName);

        return count;
    }

    @Override
    public Semester findBySemesterId(Long semesterId) {
        Semester semester = semesterRepository.findBySemesterId(semesterId);
        if (semester == null){
            throw new EvaluationException(ResultEnum.SEMESTER_NOT_EXIST);
        }

        return semester;
    }

    @Override
    public void deleteBySemesterId(Long semesterId) {
        semesterRepository.deleteBySemesterId(semesterId);
        channelControlRepository.deleteBySemesterId(semesterId);
    }
}
