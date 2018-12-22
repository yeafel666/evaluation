package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Semester;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.SemesterService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
@RequestMapping("/semester")
@Slf4j
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @GetMapping("/list")
    public ResultVO<Semester> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                   @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                   Semester semester) throws UnsupportedEncodingException {
        Integer count = semesterService.countSemesterForPage(semester.getSemesterName());

        /** 此处代码作用：  从前端传回来的course值会乱码，在这里处理掉乱码问题.*/
        if (semester.getSemesterName() != null) {
            String fname =  URLDecoder.decode(semester.getSemesterName() , "utf-8");
            semester.setSemesterName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Semester> semesterPage = semesterService.findSemesterIfSemesterNameIsNotNull(semester.getSemesterName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(semesterPage.getContent(),count);


    }
    //保存用户，编辑与新增公用
    @PostMapping("/save")
    public ResultVO<Null> save(Semester semester){

        if (semester != null && semester.getSemesterId() != null){
            Semester updateResult = semesterService.updateSemester(semester);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Semester createResult = semesterService.create(semester);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }

    @PostMapping("/updateSemester")
    public ResultVO<Null> updateSemester(Long semesterId){

        Semester semester= semesterService.findBySemesterId(semesterId);
        if (semester == null){
            throw new EvaluationException(ResultEnum.SEMESTER_NOT_EXIST);
        }

        return ResultVOUtil.success(semester);
    }

    @GetMapping("/findBySemesterId")
    public ResultVO<Semester> findBySemesterId(Long semesterId){
        Semester semester = semesterService.findBySemesterId(semesterId);
        return ResultVOUtil.success(semester);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                semesterService.deleteBySemesterId(a);
            }
        } else {
            semesterService.deleteBySemesterId(id);
        }
        return ResultVOUtil.success();
    }
}
