package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.dto.IndexDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kangyifan on 2018/10/8 9:57
 */
public class Index2IndexDTOConverter {

    public static IndexDTO convert(Index index){

        IndexDTO indexDTO = new IndexDTO();
        BeanUtils.copyProperties(index,indexDTO);
        return indexDTO;
    }

    public static List<IndexDTO> convert(List<Index> indexList){
        return indexList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
