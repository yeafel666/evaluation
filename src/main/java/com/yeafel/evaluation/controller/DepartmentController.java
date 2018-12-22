package com.yeafel.evaluation.controller;
import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Department;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.DepartmentService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public ResultVO<List<Department>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                          @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                          Department department) throws UnsupportedEncodingException {
        Integer count = departmentService.countDepartmentForPage(department.getDepartmentName());

        /** 此处代码作用：  从前端传回来的course值会乱码，在这里处理掉乱码问题.*/
        if (department.getDepartmentName()!= null) {
            String fname =  URLDecoder.decode(department.getDepartmentName() , "utf-8");
            department.setDepartmentName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Department> departmentPage= departmentService.findDepartmentIfDepartmentNameIsNotNull(department.getDepartmentName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(departmentPage.getContent(),count);


    }

    //保存用户，编辑与新增公用
    @PostMapping("/save")
    public ResultVO<Null> save(Department department){

        if (department != null && department.getDepartmentId() != null){
            Department updateResult = departmentService.updateDepartment(department);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Department createResult = departmentService.create(department);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }


    @PostMapping("/updateDepartment")
    public ResultVO<Null> updateDepartment(Long departmentId){

        Department department= departmentService.findByDepartmentId(departmentId);
        if (department == null){
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }

        return ResultVOUtil.success(department);
    }



    @GetMapping("/findByDepartmentId")
    public ResultVO<Department> findByDepartmentId(Long departmentId){
        Department department = departmentService.findByDepartmentId(departmentId);
        return ResultVOUtil.success(department);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                departmentService.deleteByDepartmentId(a);
            }
        } else {
            departmentService.deleteByDepartmentId(id);
        }
        return ResultVOUtil.success();
    }

}
