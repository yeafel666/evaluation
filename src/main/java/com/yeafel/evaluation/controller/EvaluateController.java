package com.yeafel.evaluation.controller;

import com.yeafel.evaluation.vo.ResultVO;
import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.EvaluateInfoDTO;
import com.yeafel.evaluation.dto.GradeDTO;
import com.yeafel.evaluation.dto.LectureDTO;
import com.yeafel.evaluation.dto.UserDTO;
import com.yeafel.evaluation.service.EvaluateInfoService;
import com.yeafel.evaluation.service.GradeService;
import com.yeafel.evaluation.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * Created by kangyifan on 2018/10/16 9:29
 */

@RestController
@RequestMapping("/evaluate")
@Slf4j
public class EvaluateController {


    @Autowired
    private EvaluateInfoService evaluateInfoService;

    @Autowired
    private GradeService gradeService;



    /**
     * 试卷提交
     * @param request
     * @param number
     * @return
     */
    @PostMapping("/submit")
    @Transactional
    public ResultVO<BigDecimal> submit(HttpServletRequest request, Integer number,
                                       LectureDTO lectureDTO,
                                       HttpSession session){

        List<String> stringList = new ArrayList<>();
        for (int i = 1;i<=number;i++){
            String value = request.getParameter(""+i+"");
            stringList.add(value);
        }


        BigDecimal result = new BigDecimal(0);
        for (String s : stringList){
            BigDecimal value = new BigDecimal(s);
             result = result.add(value) ;
        }

//        result = result.multiply(new BigDecimal(0.3));       //乘以上一级权重   (不再需要乘，已经处理过叶子结点的权重)

        result = result.setScale(2, RoundingMode.HALF_UP);   //保留两位小数

        Long roleId = (Long) session.getAttribute("roleId");
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        EvaluateInfo evaluateInfo = new EvaluateInfo();
        evaluateInfo.setEvaluatorId(userDTO.getUserId());
        evaluateInfo.setRoleId(roleId);
        evaluateInfo.setTeacherId(lectureDTO.getTeacherId());
        evaluateInfo.setCourseId(lectureDTO.getCourseId());
        evaluateInfo.setScore(result);
        EvaluateInfo s = evaluateInfoService.save(evaluateInfo);


        GradeDTO gradeDTO = gradeService.findByTeacherId(s);
        if (gradeDTO.getGradeId() == null){
            GradeDTO gd = new GradeDTO();
            gd.setTeacherId(s.getTeacherId());
            gd.setCourseId(s.getCourseId());
            List<EvaluateInfoDTO> evaluateInfoDTOList = evaluateInfoService.findEvaluateInfosByTeacherId(s.getTeacherId());
            BigDecimal studentScore = new BigDecimal(0);
            for (EvaluateInfoDTO evaluateInfoDTO : evaluateInfoDTOList){
                studentScore = studentScore.add(evaluateInfoDTO.getScore());
            }
            studentScore = studentScore.divide(new BigDecimal(evaluateInfoDTOList.size()),2,ROUND_HALF_DOWN);   //保留两位小数;
            if (roleId == 0){

                gd.setStudentScore(studentScore);
            }else if (roleId == 1 || roleId==9){

                gd.setTeacherScore(studentScore);
            }else if (roleId == 2){

                gd.setResearchScore(studentScore);
            }else if (roleId == 3){
                gd.setDepartmentScore(studentScore);
            }

            gd.setTotalScore(studentScore);

            gradeService.save(gd);
        }else {
            List<EvaluateInfoDTO> evaluateInfoDTOList = evaluateInfoService.findEvaluateInfosByTeacherId(s.getTeacherId());
            BigDecimal studentScore = new BigDecimal(0);Long studentNumber=0L;
            BigDecimal teacherScore = new BigDecimal(0);Long teacherNumber=0L;
            BigDecimal researchScore = new BigDecimal(0);Long researchNumber=0L;
            BigDecimal departmentScore = new BigDecimal(0);Long departmentNumber=0L;
            for (EvaluateInfoDTO evaluateInfoDTO : evaluateInfoDTOList){
                if(evaluateInfoDTO.getRoleId() == 0){          //学生
                    studentScore = studentScore.add(evaluateInfoDTO.getScore());
                    studentNumber = studentNumber + 1L;
                }else if (evaluateInfoDTO.getRoleId() == 1 || evaluateInfoDTO.getRoleId() == 9 ){
                    teacherScore = teacherScore.add(evaluateInfoDTO.getScore());
                    teacherNumber = teacherNumber + 1L;
                }else if (evaluateInfoDTO.getRoleId() == 2  ){
                    researchScore = researchScore.add(evaluateInfoDTO.getScore());
                    researchNumber = researchNumber + 1L;
                }else if (evaluateInfoDTO.getRoleId() == 3  ){
                    departmentScore = departmentScore.add(evaluateInfoDTO.getScore());
                    departmentNumber = departmentNumber + 1L;
                }
            }
            if (roleId == 0){
                studentScore = studentScore.divide(new BigDecimal(studentNumber),2,ROUND_HALF_DOWN);   //保留两位小数;
                gradeDTO.setStudentScore(gradeDTO.getStudentScore().add(studentScore));
            }else if (roleId == 1 || roleId==9){
                teacherScore = teacherScore.divide(new BigDecimal(teacherNumber),2,ROUND_HALF_DOWN);   //保留两位小数;
                gradeDTO.setTeacherScore(gradeDTO.getTeacherScore().add(teacherScore));
            }else if (roleId == 2){
                researchScore = researchScore.divide(new BigDecimal(researchNumber),2,ROUND_HALF_DOWN);   //保留两位小数;

                gradeDTO.setResearchScore(gradeDTO.getResearchScore().add(researchScore));
            }else if (roleId == 3){
                departmentScore = departmentScore.divide(new BigDecimal(departmentNumber),2,ROUND_HALF_DOWN);   //保留两位小数;

                gradeDTO.setDepartmentScore(gradeDTO.getDepartmentScore().add(departmentScore));
            }
            BigDecimal totalScore = studentScore.add(teacherScore).add(researchScore).add(departmentScore);
            gradeDTO.setTotalScore(totalScore);
            gradeService.save(gradeDTO);
        }

        return ResultVOUtil.success(result);
    }
}
