package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.Course;
import com.yeafel.evaluation.dto.GradeDTO;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.service.CourseService;
import com.yeafel.evaluation.service.GradeService;
import com.yeafel.evaluation.service.UserService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by kangyifan on 2018/10/19 2:02
 */
@RestController
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;


    //用户列表
    @GetMapping("/list")
    public ResultVO<List<GradeDTO>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                             @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                             GradeDTO gradeDTO,
                                             HttpSession session) throws UnsupportedEncodingException {
        // HttpServletResponse response)

        Integer count = gradeService.countIfTeacherId(gradeDTO.getTeacherId());

        /** 此处代码作用：  从前端传回来的username值会乱码，在这里处理掉乱码问题.*/
        if (gradeDTO.getTeacherName() != null) {
            String fname =  URLDecoder.decode(gradeDTO.getTeacherName() , "utf-8");
            gradeDTO.setTeacherName(fname);
        }


        PageRequest request = PageRequest.of(page-1,limit);
        Page<GradeDTO> gradeDTOPage = gradeService.findIfTeacherId(gradeDTO.getTeacherId(),request);
        for(GradeDTO g :gradeDTOPage.getContent()){
            UserDTO teacher= userService.findByUserId(g.getTeacherId());
            Course course = courseService.findByCourseId(g.getCourseId());
            g.setTeacherName(teacher.getUsername());
            g.setCourseName(course.getCourseName());
        }
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(gradeDTOPage.getContent(),count);
    }

    @GetMapping("/personal")
    public ResultVO<List<GradeDTO>> personal(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                             @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                             GradeDTO gradeDTO,
                                             HttpSession session) throws UnsupportedEncodingException {
        // HttpServletResponse response)

        UserDTO u = (UserDTO) session.getAttribute("user");
        gradeDTO.setTeacherId(u.getUserId());
        Integer count = gradeService.countIfTeacherId(gradeDTO.getTeacherId());

        /** 此处代码作用：  从前端传回来的username值会乱码，在这里处理掉乱码问题.*/
        if (gradeDTO.getTeacherName() != null) {
            String fname =  URLDecoder.decode(gradeDTO.getTeacherName() , "utf-8");
            gradeDTO.setTeacherName(fname);
        }


        PageRequest request = PageRequest.of(page-1,limit);
        Page<GradeDTO> gradeDTOPage = gradeService.findIfTeacherId(gradeDTO.getTeacherId(),request);
        for(GradeDTO g :gradeDTOPage.getContent()){
            UserDTO teacher= userService.findByUserId(g.getTeacherId());
            Course course = courseService.findByCourseId(g.getCourseId());
           g.setTeacherName(teacher.getUsername());
           g.setCourseName(course.getCourseName());
        }
//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(gradeDTOPage.getContent(),count);
    }
}
