package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.Grade;
import com.yeafel.evaluation.dto.GradeDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kangyifan on 2018/10/18 21:02
 */
public class Grade2GradeDTO {

    public static GradeDTO convert(Grade grade){

        GradeDTO gradeDTO = new GradeDTO();
        BeanUtils.copyProperties(grade,gradeDTO);
        return gradeDTO;
    }

    public static List<GradeDTO> convert(List<Grade> gradeList){
        return gradeList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
