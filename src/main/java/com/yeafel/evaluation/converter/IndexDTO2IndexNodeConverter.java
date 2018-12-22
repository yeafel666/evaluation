package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dto.IndexDTO;
import com.yeafel.evaluation.tree.IndexNode;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kangyifan on 2018/10/15 16:43
 */
public class IndexDTO2IndexNodeConverter {

    public static IndexNode convert(IndexDTO indexDTO){

        IndexNode indexNode = new IndexNode();
        BeanUtils.copyProperties(indexDTO,indexNode);
        return indexNode;
    }

    public static List<IndexNode> convert(List<IndexDTO> lectureList){
        return lectureList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
