package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.VO.ResultVO;
import com.yeafel.evaluation.dataobject.Department;
import com.yeafel.evaluation.dataobject.Lecture;
import com.yeafel.evaluation.dataobject.User;
import com.yeafel.evaluation.dto.ChannelControlDTO;
import com.yeafel.evaluation.dto.LectureDTO;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.enums.ResultEnum;
import com.yeafel.evaluation.exception.EvaluationException;
import com.yeafel.evaluation.service.ChannelControlService;
import com.yeafel.evaluation.service.DepartmentService;
import com.yeafel.evaluation.service.LectureService;
import com.yeafel.evaluation.service.UserService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kangyifan on 2018/10/11 9:17
 */
@RestController
@RequestMapping("/lecture")
@Slf4j
public class LectureController {


    @Autowired
    private LectureService lectureService;

    @Autowired
    private UserService userService;

    @Autowired
    private ChannelControlService channelControlService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public ResultVO<List<Lecture>> list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                        @RequestParam(value = "limit",defaultValue = "10") Integer limit,
                                        LectureDTO lectureDTO,
                                        HttpSession session) throws UnsupportedEncodingException {

        /** 此处代码作用：  从前端传回来的username值会乱码，在这里处理掉乱码问题.*/
        if (lectureDTO.getTeacherName() != null && !lectureDTO.getTeacherName().equals("")) {
            String fname =  URLDecoder.decode(lectureDTO.getTeacherName() , "utf-8");
            lectureDTO.setTeacherName(fname);
        }


        UserDTO userDTO = new UserDTO();
        /** 为搜索服务.*/
        if (lectureDTO.getTeacherName() != null && !lectureDTO.getTeacherName().equals("")){
            userDTO = userService.findByUsername(lectureDTO.getTeacherName());
        }

        Integer count = lectureService.countLectureForPage(userDTO.getUserId());


        PageRequest request = PageRequest.of(page-1,limit);
        Page<LectureDTO> lectureDTOPage = lectureService.findLecturesIfTeacherIdIsNotNull(userDTO.getUserId(),request);


        /** 因为正在评教显示的字段与授课管理类似，所以放在这个地方，
         * 另外需要给LectureDTO添加一个是否能够评教的变量作为标识.*/
        UserDTO userSession= (UserDTO)session.getAttribute("user");

        Long roleId = (Long) session.getAttribute("roleId");

        Department department = departmentService.findByDepartmentNo(userSession.getDepartmentNo());

        if (roleId != 4){       // 如果是管理员就不需要过滤数组
            for (LectureDTO L : lectureDTOPage.getContent()){
                if (roleId == 0){
                    if (!userSession.getClazzNo().equals(L.getClazzNo())){
                        L.setIsMatch(0L);       //设置为不匹配
                    }
                }else {
                    if (!department.getDepartmentId().equals(L.getDepartmentId())){
                        L.setIsMatch(0L);       //设置为不匹配
                    }
                }
            }

        }


        for (LectureDTO L : lectureDTOPage.getContent()){
            ChannelControlDTO channelControlDTO = channelControlService.findBySemesterId(L.getSemesterId());
            if (channelControlDTO.getChannelState() == 0 ){
                L.setIsOpen(0L);
            }else{
                L.setIsOpen(1L);
            }
        }





//        response.addHeader("Access-Control-Allow-Origin", "*");   使用nginx需要加
        return ResultVOUtil.success(lectureDTOPage.getContent(),count);


    }



    @GetMapping("/findByLectureId")
    public ResultVO<Lecture> findByLectureId(Long lectureId){

        LectureDTO lectureDTO = lectureService.findByLectureId(lectureId);

        return ResultVOUtil.success(lectureDTO);
    }


    @PostMapping("/save")
    public ResultVO<Null> save(LectureDTO lectureDTO){

        if (lectureDTO != null && lectureDTO.getLectureId() != null){
            LectureDTO updateResult = lectureService.update(lectureDTO);
            if (updateResult == null){
                throw new EvaluationException(ResultEnum.UPDATE_ERROR);
            }
        }else {
            LectureDTO createResult = lectureService.create(lectureDTO);
            if (createResult == null){
                throw new EvaluationException(ResultEnum.CREATE_ERROR);
            }
        }

        return ResultVOUtil.success();

    }


    @PostMapping("/delete")
    public ResultVO<Null> delete(String delitems,Long id){
        if (delitems != null) { /*判断时单行删除还是批量删除*/
            String[] strs = delitems.split(",");
            for (int i = 0; i < strs.length; i++) {
                Long a = Long.parseLong(strs[i]);
                lectureService.delete(a);
            }
        } else {
            lectureService.delete(id);
        }
        return ResultVOUtil.success();
    }







}
