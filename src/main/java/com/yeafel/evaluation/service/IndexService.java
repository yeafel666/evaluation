package com.yeafel.evaluation.service;

import com.yeafel.evaluation.dataobject.Index;
import com.yeafel.evaluation.dto.IndexDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  指标业务逻辑
 * Created by kangyifan on 2018/10/8 9:51
 */
public interface IndexService {

    Page<IndexDTO> findIndexIfIndexNameIsNotNull(String indexName, Pageable pageable);

    Integer countIndexForPage(String indexName);

    /** 通过id查询指标 .*/
    IndexDTO findByIndexId(Long indexId);


    IndexDTO updateIndex(IndexDTO indexDTO);


    IndexDTO create(IndexDTO indexDTO);


    void deleteByIndexId(Long indexId);

    List<Index> findAll();
}
