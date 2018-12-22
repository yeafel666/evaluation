package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.EvaluateInfo;
import com.yeafel.evaluation.dto.EvaluateInfoDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  评教记录  转换器
 * Created by kangyifan on 2018/10/16 14:41
 */
public class EvaluateInfo2EvaluateInfoDTOConverter {

    public static EvaluateInfoDTO convert(EvaluateInfo evaluateInfo){

        EvaluateInfoDTO evaluateInfoDTO = new EvaluateInfoDTO();
        BeanUtils.copyProperties(evaluateInfo,evaluateInfoDTO);
        return evaluateInfoDTO;
    }

    public static List<EvaluateInfoDTO> convert(List<EvaluateInfo> evaluateInfoList){
        return evaluateInfoList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
