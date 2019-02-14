package com.yeafel.evaluation.controller;


import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.Course;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.CourseService;
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
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService courseService;



    @GetMapping("/list")
    public ResultVO<List<Course>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                      Course course) throws UnsupportedEncodingException {
            Integer count = courseService.countCourseForPage(course.getCourseName());

        /** 此处代码作用：  从前端传回来的course值会乱码，在这里处理掉乱码问题.*/
        if (course.getCourseName() != null) {
            String fname =  URLDecoder.decode(course.getCourseName() , "utf-8");
            course.setCourseName(fname);
        }

        PageRequest request = PageRequest.of(page-1,limit);
        Page<Course> coursePage = courseService.findCourseIfCourseNameIsNotNull(course.getCourseName(),request);
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(coursePage.getContent(),count);


    }

    //保存用户，编辑与新增公用
    @PostMapping("/save")
    public ResultVO<Null> save(Course course){

        if (course != null && course.getCourseId() != null){
            Course updateResult = courseService.updateCourse(course);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            Course createResult = courseService.create(course);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }


    @PostMapping("/updateCourse")
    public ResultVO<Null> updateCourse(Long courseId){

        Course course= courseService.findByCourseId(courseId);
        if (course == null){
            throw new EvaluationException(ResultEnum.COURSE_NOT_EXIST);
        }

        return ResultVOUtil.success(course);
    }



    @GetMapping("/findByCourseId")
    public ResultVO<Course> findByCourseId(Long courseId){
        Course course = courseService.findByCourseId(courseId);
        return ResultVOUtil.success(course);
    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                courseService.deleteByCourseId(a);
            }
        } else {
            courseService.deleteByCourseId(id);
        }
        return ResultVOUtil.success();
    }

}
