package com.yeafel.evaluation.converter;

import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.tree.IndexNode;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kangyifan on 2018/10/8 21:09
 */
public class Index2IndexNodeConverter {

    public static IndexNode convert(Index index){

        IndexNode indexNode  = new IndexNode();
        BeanUtils.copyProperties(index,indexNode);
        return indexNode;
    }

    public static List<IndexNode> convert(List<Index> indexList){

        return indexList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}
